package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.resource.dao.KnowledgeDao;
import edu.whu.learneur.resource.service.IKnowledgeService;
import edu.whu.learneur.domain.Knowledge;
import edu.whu.learneur.exception.UserServiceException;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeDao, Knowledge> implements IKnowledgeService {

    @SelectKey(statement = "select last_insert_id",keyProperty = "id",keyColumn = "id_knowledge",resultType = Long.class,before = true)
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
}
