package edu.whu.learneur.service.impl;

import edu.whu.learneur.dao.KnowledgesResourcesDao;
import edu.whu.learneur.domain.Knowledge;
import edu.whu.learneur.dao.KnowledgesDao;
import edu.whu.learneur.service.IKnowledgesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 知识点 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
@Transactional
public class KnowledgesServiceImpl extends ServiceImpl<KnowledgesDao, Knowledge> implements IKnowledgesService {
    @Autowired
    private KnowledgesDao knowledgesDao;

    @Autowired
    private KnowledgesResourcesDao knowledgesResourcesDao;

    @Override
    public Knowledge findKnowledgeNode(Long idKnowledge, int pages, int cols) {
        Knowledge knowledge = knowledgesDao.selectById(idKnowledge);
        /* 注入电子书, 网课, 项目等资源信息 */
        return knowledge;
    }
}
