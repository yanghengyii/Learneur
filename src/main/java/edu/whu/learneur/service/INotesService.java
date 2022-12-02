package edu.whu.learneur.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Notes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 学习笔记 服务类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
public interface INotesService extends IService<Notes> {
    /**
     * 查询用户所写的笔记
     * @param idUser    用户id
     * @param pages     页数
     * @param cols      列数
     * @return
     */
    IPage<Notes> findNotesByUser(Long idUser, int pages, int cols);

    /**
     * 查询用户所写的某一个资源的笔记
     * @param idUser        用户id
     * @param idResources   资源id
     * @param pages         页数
     * @param cols          列数
     * @return
     */
    IPage<Notes> findNotesByUserAndResources(Long idUser, Long idResources, int pages, int cols);

    /**
     * 查询某一个资源的笔记
     * @param idResources   资源id
     * @param pages         页数
     * @param cols          列数
     * @return
     */
    IPage<Notes> findNotesByResources(Long idResources, int pages, int cols);

    /**
     * 查询一篇文章详细信息
     * @param idNote
     * @return
     */
    Notes findNotes(Long idNote);

    /**
     * 删除一篇笔记
     * @param idNote    笔记id
     * @return
     */
    boolean deleteNote(Long idNote);

    /**
     * 删除一组笔记
     * @param idNotes   笔记ids
     * @return
     */
    boolean deleteNoteByBatch(List<Long> idNotes);
}
