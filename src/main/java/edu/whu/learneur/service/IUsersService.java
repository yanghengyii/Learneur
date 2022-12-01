package edu.whu.learneur.service;

import edu.whu.learneur.domain.Roles;
import edu.whu.learneur.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.exception.UserServiceException;
import org.apache.ibatis.exceptions.TooManyResultsException;

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
     * 用户注册操作
     * @param user  用户对象
     * @return      操作后的用户对象
     * @throws UserServiceException
     */
    Users register(Users user) throws UserServiceException;
}
