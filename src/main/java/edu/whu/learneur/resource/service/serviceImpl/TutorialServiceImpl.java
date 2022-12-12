package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.resource.dao.TutorialDao;
import edu.whu.learneur.resource.entity.Tutorial;
import edu.whu.learneur.resource.service.ITutorialService;
import edu.whu.learneur.exception.UserServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorialServiceImpl extends ServiceImpl<TutorialDao, Tutorial> implements ITutorialService {

    public List<Tutorial> addTutorial(List<Tutorial> tutorialList) throws UserServiceException {
        List<Tutorial> success = new ArrayList<>();
        for(Tutorial tutorial : tutorialList){
            LambdaQueryWrapper<Tutorial> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Tutorial::getName,tutorial.getName());
            Tutorial tmp = getBaseMapper().selectOne(lqw);
            if(tmp == null){
                getBaseMapper().insert(tutorial);
                success.add(tutorial);
            }
            else if(!tmp.equals(tutorial)) {
                tutorial.setId(tmp.getId());
                getBaseMapper().updateById(tutorial);

            }
        }
        return success;
    }

    public IPage<Tutorial> findTutorialPage(Long knowledgeId, Integer pageNum, Integer pageSize) {
        return getBaseMapper().findTutorialsByKnowledgeId(knowledgeId, new Page<>(pageNum, pageSize));
    }

    public Tutorial findById(long id){
        return getBaseMapper().selectById(id);
    }
}
