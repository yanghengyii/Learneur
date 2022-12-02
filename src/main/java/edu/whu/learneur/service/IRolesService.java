package edu.whu.learneur.service;

import edu.whu.learneur.domain.Roles;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
public interface IRolesService extends IService<Roles> {

    /**
     * 通过id查找Role
     * @param idRole    Role id
     * @return
     */
    Roles findRoleById(Long idRole);

    /**
     * 通过角色名获取角色
     * @param name  角色名
     * @return
     */
    Roles findRoleByName(String name);
}
