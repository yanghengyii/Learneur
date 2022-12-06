package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.resource.dao.KnowledgeDao;
import edu.whu.learneur.resource.service.IKnowledgeService;
import edu.whu.learneur.domain.Knowledge;
import edu.whu.learneur.exception.UserServiceException;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeDao, Knowledge> implements IKnowledgeService {
    public void addKnowledge(Knowledge knowledge) throws UserServiceException {
            LambdaQueryWrapper<Knowledge> lqw = new LambdaQueryWrapper<>();
            lqw.like(Knowledge::getKnowledgeName,knowledge.getKnowledgeName());
            if(getBaseMapper().selectList(lqw).size()==0){
                getBaseMapper().insert(knowledge);
            }
            else{
                throw new UserServiceException("知识点已存在");
            }
    }

    public Knowledge findById(Long id){
        return getBaseMapper().selectById(id);
    }

    public List<Knowledge> findAll() {
        return getBaseMapper().findAll();
    }

    public Knowledge findByName(String name){
        LambdaQueryWrapper<Knowledge> lqw = new LambdaQueryWrapper<Knowledge>();
        lqw.like(Knowledge::getKnowledgeName,name);
        Knowledge result = new Knowledge();
        try{
            result = getBaseMapper().selectList(lqw).get(0);
        }
        catch (Exception e){
            throw new UserServiceException("无此知识点");
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
}
