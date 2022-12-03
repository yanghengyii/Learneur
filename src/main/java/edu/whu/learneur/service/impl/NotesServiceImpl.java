package edu.whu.learneur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.domain.Notes;
import edu.whu.learneur.dao.NotesDao;
import edu.whu.learneur.exception.NotesServiceException;
import edu.whu.learneur.service.INotesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 学习笔记 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
@Transactional
public class NotesServiceImpl extends ServiceImpl<NotesDao, Notes> implements INotesService {
    @Autowired
    private NotesDao notesDao;

    @Override
    public IPage<Notes> findNotesByUser(Long idUser, int pages, int cols) {
        LambdaQueryWrapper<Notes> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Notes::getNoteAuthorId, idUser);
        return notesDao.selectPage(new Page<>(pages, cols), lqw);
    }

    @Override
    public IPage<Notes> findNotesByUserAndResources(Long idUser, Long idResources, int pages, int cols) {
        LambdaQueryWrapper<Notes> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Notes::getNoteAuthorId, idUser).eq(Notes::getIdResources, idResources);
        return notesDao.selectPage(new Page<>(pages, cols), lqw);
    }

    @Override
    public IPage<Notes> findNotesByResources(Long idResources, int pages, int cols) {
        LambdaQueryWrapper<Notes> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Notes::getIdResources, idResources);
        return notesDao.selectPage(new Page<>(pages, cols), lqw);
    }

    @Override
    public Notes findNotes(Long idNote) {
        return notesDao.selectById(idNote);
    }

    @Override
    public boolean deleteNote(Long idNote) {
        return notesDao.deleteById(idNote) > 0;
    }

    @Override
    public boolean deleteNoteByBatch(List<Long> idNotes) {
        int res = notesDao.deleteBatchIds(idNotes);
        if(res != idNotes.size()) {
            throw new NotesServiceException("删除出错!");
        }
        return true;
    }
}
