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
     * 通告id查找Role
     * @param idRole    Role id
     * @return
     */
    Roles findRoleById(Long idRole);
}
