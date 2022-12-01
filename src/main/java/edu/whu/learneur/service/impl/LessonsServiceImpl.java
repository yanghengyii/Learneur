package edu.whu.learneur.service.impl;

import edu.whu.learneur.domain.Lessons;
import edu.whu.learneur.dao.LessonsDao;
import edu.whu.learneur.service.ILessonsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网课 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
public class LessonsServiceImpl extends ServiceImpl<LessonsDao, Lessons> implements ILessonsService {

}
