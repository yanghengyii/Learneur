package edu.whu.learneur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.domain.Roles;
import edu.whu.learneur.domain.Users;
import edu.whu.learneur.dao.UsersDao;
import edu.whu.learneur.dto.ChangeEmailDTO;
import edu.whu.learneur.dto.ForgetPasswordDTO;
import edu.whu.learneur.dto.RegisterUserDTO;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.service.IRolesService;
import edu.whu.learneur.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
@Transactional
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements IUsersService {
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private IRolesService rolesService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /* 默认头像 */
    private final static String DEFAULT_AVATAR_IMG_PATH = "https://c-ssl.duitang.com/uploads/blog/202012/26/20201226223704_3f25a.jpg";

    @Override
    public Users findUserById(Long idUser) throws UserServiceException {
        Users user = usersDao.selectById(idUser);
        if(Objects.isNull(user)) {
            throw new UserServiceException("用户不存在!" );
        }
        return user;
    }

    @Override
    public Users findUserByUsername(String username) throws UserServiceException {
        Users user = null;
        LambdaQueryWrapper<Users> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Users::getUsername, username);
        user = usersDao.selectOne(lqw);
        if(Objects.isNull(user)) {
            throw new UserServiceException("用户" + username + "不存在!");
        }
        return user;
    }

    @Override
    public Roles findUserRoleByUsername(String username) {
        LambdaQueryWrapper<Users> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Users::getUsername, username);
        Long idRole = usersDao.selectOne(lqw).getIdRole();
        return rolesService.findRoleById(idRole);
    }

    @Override
    public Users findUserByEmail(String email) {
        LambdaQueryWrapper<Users> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Users::getEmail, email);
        Users users = usersDao.selectOne(lqw);
        return users;
    }

    @Override
    public boolean register(RegisterUserDTO user) throws UserServiceException {
        String verificationCode = redisTemplate.boundValueOps(user.getEmail()).get();
        if(!StringUtil.isNullOrEmpty(verificationCode)) {
            if(verificationCode.equals(user.getVerificationCode())) {
                // 创建一个新用户并返回
                Users newUser = new Users();
                newUser.setUsername(user.getEmail());
                newUser.setPassword(user.getPassword());   // 此处传递的是密文密码
                newUser.setRealName(user.getReal_name());
                newUser.setAvatarUrl(DEFAULT_AVATAR_IMG_PATH);
                newUser.setEmail(user.getEmail());
                newUser.setPhone("");
                LocalDateTime now = LocalDateTime.now();
                newUser.setRegisterTime(now);
                newUser.setLastLoginTime(null);
                newUser.setIdRole(rolesService.findRoleByName("user").getRoleId());
                redisTemplate.delete(user.getEmail());
                int res = usersDao.insert(newUser);
                return res > 0;
            }
            else throw new UserServiceException("验证码错误");
        }
        throw new UserServiceException("邮件已过期, 请重新发送");
    }

    @Override
    public boolean forgetPassword(ForgetPasswordDTO user) throws UserServiceException {
        String email = redisTemplate.boundValueOps(user.getCode()).get();
        if(!StringUtil.isNullOrEmpty(email)) {
            boolean res = updatePasswordByEmail(user.getPassword(), email);
            if(!res) {
                throw new UserServiceException("更新失败!");
            }
            redisTemplate.delete(user.getCode());
            return true;
        }
        throw new UserServiceException("邮件已过期, 请重新发送");
    }

    @Override
    public void updateOnlineStatusByUsername(String username) {
        LambdaUpdateWrapper<Users> luw = new LambdaUpdateWrapper<>();
        luw.set(Users::getOnline, "1").eq(Users::getUsername, username);
        usersDao.update(null, luw);
    }

    @Override
    public boolean updateUserInfoByUsername(String username, Users user) {
        Users oldUser = findUserByUsername(username);
        if(Objects.isNull(oldUser) || Objects.isNull(user)) {
            throw new UserServiceException("用户不存在!");
        }
        /* 保存一些不可修改字段 */
        Long idUser = oldUser.getUserId();
        LocalDateTime registerTime = oldUser.getRegisterTime();
        String email = oldUser.getEmail();
        BeanUtils.copyProperties(user, oldUser);
        /* 恢复原值, 如果改变了的话 */
        oldUser.setIdRole(idUser);
        oldUser.setRegisterTime(registerTime);
        oldUser.setEmail(email);
        return usersDao.updateById(oldUser) > 0;
    }

    @Override
    public IPage<Users> findUsers(boolean online, int pages, int cols) {
        LambdaQueryWrapper<Users> lqw = new LambdaQueryWrapper<>();
        if(online) {
            lqw.eq(Users::getOnline, '1');
        }
        return usersDao.selectPage(new Page<>(pages, cols), lqw);
    }

    @Override
    public boolean deleteUsersByUsername(String username) {
        LambdaQueryWrapper<Users> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Users::getUsername, username);
        Users user = usersDao.selectOne(lqw);
        if(Objects.isNull(user)) {
            throw new UserServiceException("试图删除未知用户!");
        }
        return usersDao.deleteById(user) > 0;
    }

    @Override
    public boolean deleteUsersByBatches(List<Long> idUsers) {
        int ret = usersDao.deleteBatchIds(idUsers);
        if(ret != idUsers.size()) {
            throw new UserServiceException("删除数量不匹配, 存在错误信息!");
        }
        return true;
    }

    @Override
    public boolean changeEmail(ChangeEmailDTO changeEmailDTO) {
        String email = redisTemplate.boundValueOps(changeEmailDTO.getCode()).get();
        if(StringUtil.isNullOrEmpty(email)) {
            throw new UserServiceException("试图修改位置用户的邮箱!");
        }
        LambdaUpdateWrapper<Users> luw = new LambdaUpdateWrapper<>();
        luw.set(Users::getEmail, email).eq(Users::getUsername, changeEmailDTO.getUsername());
        return usersDao.update(null, luw) > 0;
    }

    /**
     * 通过邮件地址更新密码(外部不能调用)
     * @param password  修改后的密码
     * @param email     邮件地址
     * @return
     */
    private boolean updatePasswordByEmail(String password, String email) {
        LambdaUpdateWrapper<Users> luw = new LambdaUpdateWrapper<>();
        luw.set(Users::getPassword, password).eq(Users::getEmail, email);
        return usersDao.update(null, luw) > 0;
    }

}
