package edu.whu.learneur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.whu.learneur.domain.Roles;
import edu.whu.learneur.domain.Users;
import edu.whu.learneur.dao.UsersDao;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.service.IRolesService;
import edu.whu.learneur.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements IUsersService {
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private IRolesService rolesService;

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
    public Users register(Users user) throws UserServiceException {
        return null;
    }
}
