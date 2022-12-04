package edu.whu.learneur.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Lessons;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 网课 服务类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
public interface ILessonsService extends IService<Lessons> {
    /**
     * 根据网课id查找相关信息
     * @param idLesson    电子数id
     * @return
     */
    Lessons findLesson(Long idLesson);

    /**
     * 获取网课资源
     * @param pages 页数
     * @param cols  行数
     * @return
     */
    IPage<Lessons> findLessons(int pages, int cols);

        /**
     * 获取一批项目资源
     * @param idLessons     项目id
     * @param pages         页数
     * @param cols          列数
     * @return
     */
    IPage<Lessons> findLessonsByBatch(List<Long> idLessons, int pages, int cols);

    /**
     * 保存一本网课资源
     * @param lesson    网课
     * @return
     */
    Lessons insertLesson(Lessons lesson);

    /**
     * 保存或更新网课
     * @param lesson    网课
     * @return
     */
    Lessons saveOrUpdateLesson(Lessons lesson);

    /**
     * 删除一本网课资源
     * @param idLesson    网课id
     * @return
     */
    boolean deleteLesson(Long idLesson);

    /**
     * 删除一组网课
     * @param idLessons   一批网课id
     * @return
     */
    boolean deleteLessons(List<Long> idLessons);

    /**
     * 修改部分Lesson资源信息
     * @param idLesson    网课id
     * @param lesson      修改后的网课数据
     * @return
     */
    boolean updateLesson(Long idLesson, Lessons lesson);
}
