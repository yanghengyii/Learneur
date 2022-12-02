package edu.whu.learneur.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Roles;
import edu.whu.learneur.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.dto.ChangeEmailDTO;
import edu.whu.learneur.dto.ForgetPasswordDTO;
import edu.whu.learneur.dto.RegisterUserDTO;
import edu.whu.learneur.exception.UserServiceException;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
public interface IUsersService extends IService<Users> {

    /**
     * 通告user id查询user
     * @param idUser    user id
     * @return 用户对象
     * @throws UserServiceException
     */
    Users findUserById(Long idUser) throws UserServiceException;

    /**
     * 通过用户名查找用户
     * @param username  用户名
     * @return  用户对象
     * @throws UserServiceException
     */
    Users findUserByUsername(String username) throws UserServiceException;

    /**
     * 通过用户名查找用户角色
     * @param username  用户名
     * @return 该用户角色
     * @throws TooManyResultsException
     */
    Roles findUserRoleByUsername(String username);

    /**
     * 通过邮件地址查询用户
     * @param email 邮件地址
     * @return  用户对象
     */
    Users findUserByEmail(String email);

    /**
     * 用户注册操作
     * @param user  用户对象
     * @return      操作结果
     * @throws UserServiceException
     */
    boolean register(RegisterUserDTO user) throws UserServiceException;

    /**
     * 忘记密码通过邮件注册操作
     * @param user  用户对象
     * @return      操作结果
     * @throws UserServiceException
     */
    boolean forgetPassword(ForgetPasswordDTO user) throws UserServiceException;

    /**
     * 更改用户登录状态
     *
     * @param username 用户名
     */
    void updateOnlineStatusByUsername(String username);

    /**
     * 更新用户对象
     * @param user  修改后的用户对象
     * @return
     */
    boolean updateUserInfoByUsername(String username, Users user);

    /**
     * 管理员使用, 查看所有用户列表
     * @param online true表名查询在线用户; 否则查询所有用户
     * @param pages 页数
     * @param cols 行数
     * @return
     */
    IPage<Users> findUsers(boolean online, int pages, int cols);

    /**
     * 删除用户, 仅限用户注销或管理员使用
     * @param username   待删除的用户id
     * @return
     */
    boolean deleteUsersByUsername(String username);

    /**
     * 删除一组用户
     * @param idUsers   这一组用户的id
     * @return
     */
    boolean deleteUsersByBatches(List<Long> idUsers);

    /**
     * 修改邮箱
     * @param changeEmailDTO    修改邮箱数据传输对象: code, username, email
     * @return
     */
    boolean changeEmail(ChangeEmailDTO changeEmailDTO);
}
