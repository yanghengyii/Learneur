package edu.whu.learneur.service.impl;

import edu.whu.learneur.domain.Notes;
import edu.whu.learneur.dao.NotesDao;
import edu.whu.learneur.service.INotesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学习笔记 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
public class NotesServiceImpl extends ServiceImpl<NotesDao, Notes> implements INotesService {

}
