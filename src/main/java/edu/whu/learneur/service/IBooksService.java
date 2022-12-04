package edu.whu.learneur.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Books;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 书籍 服务类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
public interface IBooksService extends IService<Books> {
    /**
     * 根据电子书id查找相关信息
     * @param idBook    电子数id
     * @return
     */
    Books findBook(Long idBook);

    /**
     * 获取电子书资源
     * @param pages 页数
     * @param cols  行数
     * @return
     */
    IPage<Books> findBooks(int pages, int cols);

    /**
     * 获取一批项目资源
     * @param idBooks       项目id
     * @param pages         页数
     * @param cols          列数
     * @return
     */
    IPage<Books> findBooksByBatch(List<Long> idBooks, int pages, int cols);

    /**
     * 保存一本电子书资源
     * @param book  电子书
     * @return
     */
    Books insertBook(Books book);

    /**
     * 保存或更新书籍资源
     * @param books 书籍
     * @return
     */
    Books saveOrUpdateBook(Books books);

    /**
     * 删除一本电子书资源
     * @param idBook    电子书id
     * @return
     */
    boolean deleteBook(Long idBook);

    /**
     * 删除一组电子书
     * @param idBooks   一批电子书id
     * @return
     */
    boolean deleteBooks(List<Long> idBooks);

    /**
     * 修改部分book资源信息
     * @param idBook    电子书id
     * @param book      修改后的电子书数据
     * @return
     */
    boolean updateBook(Long idBook, Books book);
}
