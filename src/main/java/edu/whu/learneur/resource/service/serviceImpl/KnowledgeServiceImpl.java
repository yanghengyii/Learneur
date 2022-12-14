package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.exception.ResourceException;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.dao.KnowledgeDao;
import edu.whu.learneur.resource.entity.Knowledge;
import edu.whu.learneur.resource.service.IKnowledgeService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeDao, Knowledge> implements IKnowledgeService {
    public void addKnowledge(Knowledge knowledge) throws ResourceException {
            LambdaQueryWrapper<Knowledge> lqw = new LambdaQueryWrapper<>();
            lqw.like(Knowledge::getKnowledgeName,knowledge.getKnowledgeName());
            if(getBaseMapper().selectList(lqw).isEmpty()){
                getBaseMapper().insert(knowledge);
            }
            else{
                throw new ResourceException("知识点已存在");
            }
    }

    public Knowledge findById(Long id){
        return getBaseMapper().selectById(id);
    }

    public List<Knowledge> findAll() {
        return getBaseMapper().findAll();
    }

    public Knowledge findByName(String name) throws ResourceException{
        LambdaQueryWrapper<Knowledge> lqw = new LambdaQueryWrapper<>();
        lqw.like(Knowledge::getKnowledgeName,name);
        Knowledge result;
        try{
            result = getBaseMapper().selectList(lqw).get(0);
        }
        catch (Exception e){
            throw new ResourceException("无此知识点");
        }
        return result;
    }

    public IPage<Knowledge> findKnowledge(Integer pageNum, Integer  pageSize) {
        Page<Knowledge> page= new Page<>(pageNum, pageSize);
        QueryWrapper<Knowledge> qw = new QueryWrapper<>();
        qw.orderByAsc("id_knowledge");
        getBaseMapper().selectPage(page, qw);
        return page;
    }

    public IPage<Knowledge> findTop(Integer pageNum, Integer  pageSize) {
        return getBaseMapper().findTop(new Page<Knowledge>(pageNum, pageSize));
    }
}
