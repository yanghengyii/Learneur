package edu.whu.learneur.crawler.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.crawler.dao.TutorialDao;
import edu.whu.learneur.crawler.entity.Book;
import edu.whu.learneur.crawler.entity.Tutorial;
import edu.whu.learneur.crawler.service.ITutorialService;
import edu.whu.learneur.exception.UserServiceException;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorialService extends ServiceImpl<TutorialDao, Tutorial> implements ITutorialService {

    @SelectKey(statement = "select last_insert_id()",keyProperty = "id",keyColumn = "id_tutorial",resultType = Long.class,before = true)
    public List<Tutorial> addBooks(List<Tutorial> tutorialList) throws UserServiceException {
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
