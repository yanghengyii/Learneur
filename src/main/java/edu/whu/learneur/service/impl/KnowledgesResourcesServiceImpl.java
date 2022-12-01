package edu.whu.learneur.service.impl;

import edu.whu.learneur.domain.KnowledgesResources;
import edu.whu.learneur.dao.KnowledgesResourcesDao;
import edu.whu.learneur.service.IKnowledgesResourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 知识点与资源关联表 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
public class KnowledgesResourcesServiceImpl extends ServiceImpl<KnowledgesResourcesDao, KnowledgesResources> implements IKnowledgesResourcesService {

}
