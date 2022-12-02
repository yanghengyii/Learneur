package edu.whu.learneur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.whu.learneur.domain.Roles;
import edu.whu.learneur.dao.RolesDao;
import edu.whu.learneur.service.IRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesDao, Roles> implements IRolesService {
    @Autowired
    RolesDao rolesDao;
    @Override
    public Roles findRoleById(Long idRole) {
        return rolesDao.selectById(idRole);
    }

    @Override
    public Roles findRoleByName(String name) {
        LambdaQueryWrapper<Roles> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Roles::getName, name);
        return rolesDao.selectOne(lqw);
    }
}
