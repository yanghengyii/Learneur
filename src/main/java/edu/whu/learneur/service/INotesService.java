package edu.whu.learneur.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Notes;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.domain.Users;

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
     * 根据创建时间获取文章
     * @param pages
     * @param cols
     * @return
     */
    IPage<Notes> findNotesOrderByCreateTime(int pages, int cols);

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

    /**
     * 上传笔记
     * @param note          笔记对象, 前端传递数据: 笔记标题, 笔记内容即可
     * @param idUser        作者信息, 与作者的关联信息
     * @param idResource    所属资源, 与资源的关联信息
     * @return
     */
    Notes postNote(Notes note, Long idUser, Long idResource);

    /**
     * 增加浏览操作
     * @param idNote    浏览的文章
     * @return
     */
    boolean incrementViewCount(Long idNote);

    /**
     * 增加点赞操作
     * @param idNote    笔记id
     * @param idUser    用户id
     * @return
     */
    boolean thumbUp(Long idNote, Long idUser);

    /**
     * 检查用户是否点赞
     * @param idNote    笔记id
     * @param idUser    用户id
     * @return          点赞返回true
     */
    boolean checkThumbUp(Long idNote, Long idUser);

    /**
     * 获得点赞总数
     * @param idNote    笔记id
     * @return
     */
    int thumbUpCounts(Long idNote);

    /**
     * 更新笔记信息
     * @param note      笔记
     * @param idNote    笔记id
     * @return
     */
    Notes updateNote(Long idNote, Notes note);

    /**
     * 查询全部笔记, 仅供管理员使用
     * @param pages     页面数
     * @param cols      列数
     * @return
     */
    IPage<Notes> findNotes(int pages, int cols);
}
