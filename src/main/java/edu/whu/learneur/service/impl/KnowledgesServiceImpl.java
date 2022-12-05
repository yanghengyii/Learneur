package edu.whu.learneur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.dao.KnowledgesResourcesDao;
import edu.whu.learneur.domain.Knowledges;
import edu.whu.learneur.dao.KnowledgesDao;
import edu.whu.learneur.domain.KnowledgesResources;
import edu.whu.learneur.domain.Resources;
import edu.whu.learneur.exception.ResourcesServiceException;
import edu.whu.learneur.service.IKnowledgesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.service.IResourcesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
public class KnowledgesServiceImpl extends ServiceImpl<KnowledgesDao, Knowledges> implements IKnowledgesService {
    @Autowired
    private KnowledgesDao knowledgesDao;

    @Autowired
    private KnowledgesResourcesDao knowledgesResourcesDao;

    @Autowired
    private IResourcesService resourcesService;

    @Override
    public Knowledges findKnowledgeNode(Long idKnowledge, int pages, int cols) {
        Knowledges knowledge = knowledgesDao.selectById(idKnowledge);
        /* 注入电子书, 网课, 项目等资源信息 */
        knowledge.setBooks(knowledgesDao.selectBooksByKnowledge(idKnowledge, new Page<>(pages, cols)));
        knowledge.setLessons(knowledgesDao.selectLessonsByKnowledge(idKnowledge, new Page<>(pages, cols)));
        knowledge.setProjects(knowledgesDao.selectProjectsByKnowledge(idKnowledge, new Page<>(pages, cols)));
        return knowledge;
    }

    @Override
    public List<Knowledges> findKnowledgeByResource(Long idResource) {
        List<KnowledgesResources> kps = knowledgesResourcesDao.selectList(
                new LambdaQueryWrapper<KnowledgesResources>().eq(KnowledgesResources::getIdResources, idResource)
        );
        List<Long> knowledges = new ArrayList<>();
        kps.forEach(kp -> {
            knowledges.add(kp.getIdKnowledge());
        });
        return knowledgesDao.selectBatchIds(knowledges);
    }

    @Override
    public IPage<Knowledges> findKnowledges(int pages, int cols) {
        return knowledgesDao.selectPage(new Page<>(pages, cols), null);
    }

    @Override
    public boolean saveOrUpdateKnowledge(Knowledges knowledge) {
        LambdaQueryWrapper<Knowledges> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Knowledges::getKnowledgeName, knowledge);
        Knowledges oldKnowledge = knowledgesDao.selectOne(lqw);
        if(Objects.isNull(oldKnowledge)) {
            return knowledgesDao.insert(knowledge) > 0;
        }
        else {
            Long id = oldKnowledge.getIdKnowledge();
            BeanUtils.copyProperties(knowledge, oldKnowledge);
            oldKnowledge.setIdKnowledge(id);
            return knowledgesDao.updateById(knowledge) > 0;
        }
    }

    @Override
    public boolean deleteKnowledge(Long idKnowledge) {
        LambdaQueryWrapper<KnowledgesResources> lqw = new LambdaQueryWrapper<>();
        lqw.eq(KnowledgesResources::getIdKnowledge, idKnowledge);
        knowledgesResourcesDao.delete(lqw);
        if(knowledgesDao.deleteById(idKnowledge) <= 0) {
            throw new ResourcesServiceException("知识点删除失败");
        }
        return true;
    }

    @Override
    public boolean deleteKnowledges(List<Long> idKnowledges) {
        boolean ret = true;
        for (Long id : idKnowledges) {
            ret &= deleteKnowledge(id);
        }
        return ret;
    }

    @Override
    public boolean addKnowledgeResourceRelation(Long idKnowledge, List<Long> idResources) {
        Knowledges knowledge = knowledgesDao.selectById(idKnowledge);
        if(Objects.isNull(knowledge)) {
            throw new ResourcesServiceException("知识点不存在");
        }
        boolean ret = true;
        for (Long idResource : idResources) {
            Resources resource = resourcesService.getById(idResource);
            if(Objects.isNull(resource)) {
                throw new ResourcesServiceException("资源不存在");
            }
            ret &= knowledgesResourcesDao.insert(new KnowledgesResources(idKnowledge, idResource)) == 1;
        }
        if(!ret) {
            throw new ResourcesServiceException("批保存失败");
        }
        return true;
    }

    @Override
    public boolean deleteKnowledgeResourceRelation(Long idKnowledge, List<Long> idResources) {
        boolean ret = true;
        for (Long idResource : idResources) {
            LambdaQueryWrapper<KnowledgesResources> lqw = new LambdaQueryWrapper<>();
            lqw.eq(KnowledgesResources::getIdKnowledge, idKnowledge)
                    .eq(KnowledgesResources::getIdResources, idResource);
            ret &= knowledgesResourcesDao.delete(lqw) == 1;
        }
        if(!ret) {
            throw new ResourcesServiceException("批删除失败");
        }
        return true;
    }
}
