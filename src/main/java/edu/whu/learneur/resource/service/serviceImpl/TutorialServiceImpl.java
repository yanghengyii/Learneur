package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
            lqw.like(Tutorial::getName,tutorial.getName());
            if(getBaseMapper().selectList(lqw).size()==0){
                getBaseMapper().insert(tutorial);
                success.add(tutorial);
            }
        }
        return success;
    }

    public Tutorial findById(long id){
        return getBaseMapper().selectById(id);
    }
}
