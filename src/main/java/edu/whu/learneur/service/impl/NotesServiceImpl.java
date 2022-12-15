package edu.whu.learneur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.dao.ThumbUpsDao;
import edu.whu.learneur.domain.Notes;
import edu.whu.learneur.dao.NotesDao;
import edu.whu.learneur.domain.ThumbUps;
import edu.whu.learneur.domain.Users;
import edu.whu.learneur.exception.NotesServiceException;
import edu.whu.learneur.service.INotesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private ThumbUpsDao thumbUpsDao;

    private static final int MAX_PREVIEW = 200;

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
        Notes note = notesDao.selectById(idNote);
        //note.setNoteThumbsUpCount(thumbUpCounts(note.getNoteId()));
        return note;
    }

    @Override
    public IPage<Notes> findNotesOrderByCreateTime(int pages, int cols) {
        LambdaQueryWrapper<Notes> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Notes::getCreatedTime);
        return notesDao.selectPage(new Page<>(pages, cols), lqw);
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

    @Override
    public Notes postNote(Notes note, Long idUser, Long idResource) {
        String content = note.getNoteContent();
        String title = note.getNoteTitle();
        String beforePrevice = content;
        String preview = beforePrevice.substring(0, Math.min(beforePrevice.length(), MAX_PREVIEW));
        note.setNoteAuthorId(idUser);     // 设置作者信息
        note.setIdResources(idResource);
        note.setNotePreviewContent(preview);
        LocalDateTime now = LocalDateTime.now();
        note.setCreatedTime(now);
        note.setUpdatedTime(now);
        if (notesDao.insert(note) < 1) {
            return null;
        }
        return note;
    }

    @Override
    public boolean incrementViewCount(Long idNote) {
        LambdaUpdateWrapper<Notes> luw = new LambdaUpdateWrapper<>();
        Integer count = notesDao.selectById(idNote).getNoteViewCount();
        luw.eq(Notes::getNoteId, idNote).set(Notes::getNoteViewCount, count + 1);
        return notesDao.update(null, luw) > 0;
    }

    @Override
    public boolean thumbUp(Long idNote, Long idUser) {
        ThumbUps thumbUps = new ThumbUps();
        thumbUps.setIdNote(idNote);
        thumbUps.setIdUser(idUser);
        LambdaQueryWrapper<ThumbUps> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ThumbUps::getIdUser, idUser).eq(ThumbUps::getIdNote, idNote);
        if(Objects.nonNull(thumbUpsDao.selectOne(lqw))) {
            /* 原状态为点赞, 再次点击取消点赞 */
            return thumbUpsDao.delete(lqw) > 0;
        }
        else {
            /* 点赞操作 */
            return thumbUpsDao.insert(thumbUps) > 0;
        }
    }

    @Override
    public boolean checkThumbUp(Long idNote, Long idUser) {
        LambdaQueryWrapper<ThumbUps> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ThumbUps::getIdUser, idUser).eq(ThumbUps::getIdNote, idNote);
        return Objects.nonNull(thumbUpsDao.selectOne(lqw));
    }

    @Override
    public int thumbUpCounts(Long idNote) {
        LambdaQueryWrapper<ThumbUps> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ThumbUps::getIdNote, idNote);
        return thumbUpsDao.selectList(lqw).size();
    }

    @Override
    public Notes updateNote(Long idNote, Notes note) {
        Notes oldNote = notesDao.selectById(idNote);
        if(Objects.isNull(oldNote)) {
            throw new NotesServiceException("修改位置文章");
        }
        /* 保存一些不应修改的字段 */
        LocalDateTime createTime = oldNote.getCreatedTime();
        /* 复制新字段 */
        BeanUtils.copyProperties(note, oldNote);
        oldNote.setNoteId(idNote);
        oldNote.setCreatedTime(createTime);
        oldNote.setUpdatedTime(LocalDateTime.now());
        if (notesDao.updateById(oldNote) < 1) {
            throw new NotesServiceException("修改失败");
        }
        return oldNote;
    }

    @Override
    public IPage<Notes> findNotes(int pages, int cols) {
        return notesDao.selectPage(new Page<>(pages, cols), new LambdaQueryWrapper<>());
    }
}
