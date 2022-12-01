package edu.whu.learneur.service.impl;

import edu.whu.learneur.domain.Resources;
import edu.whu.learneur.dao.ResourcesDao;
import edu.whu.learneur.service.IResourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesDao, Resources> implements IResourcesService {

}
