package edu.whu.learneur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.aspect.ResourcesLogger;
import edu.whu.learneur.constant.ResourcesType;
import edu.whu.learneur.domain.Books;
import edu.whu.learneur.dao.BooksDao;
import edu.whu.learneur.exception.ResourcesServiceException;
import edu.whu.learneur.service.IBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 书籍 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
@ResourcesLogger(type = ResourcesType.BOOK)
public class BooksServiceImpl extends ServiceImpl<BooksDao, Books> implements IBooksService {
    @Autowired
    private BooksDao booksDao;

    private static final String DEFAULT_COVER = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.shinerayad.com%2Fupfile%2Fattached%2F2017620163938927844.jpg&refer=http%3A%2F%2Fwww.shinerayad.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672644118&t=8eb9947cefaefba42b53a1a827ecff6c";

    @Override
    public Books findBook(Long idBook) {
        return booksDao.selectById(idBook);
    }

    @Override
    public IPage<Books> findBooks(int pages, int cols) {
        return booksDao.selectPage(new Page<>(pages, cols), new LambdaQueryWrapper<>());
    }

    @Override
    public IPage<Books> findBooksByBatch(List<Long> idBooks, int pages, int cols) {
        LambdaQueryWrapper<Books> lqw = new LambdaQueryWrapper<>();
        lqw.in(Books::getIdBook, idBooks);
        return booksDao.selectPage(new Page<>(pages, cols), lqw);
    }

    @Override
    @Transactional
    public boolean insertBook(Books book) {
        if("".equals(book.getImgPath().trim())) {
            book.setPath(DEFAULT_COVER);
        }
        return booksDao.insert(book) > 0;
    }

    @Override
    @Transactional
    public boolean deleteBook(Long idBook) {
        return booksDao.deleteById(idBook) > 0;
    }

    @Override
    @Transactional
    public boolean deleteBooks(List<Long> idBooks) {
        int res = booksDao.deleteBatchIds(idBooks);
        if(res != idBooks.size()) {
            throw new ResourcesServiceException("电子书组删除失败");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateBook(Long idBook, Books book) {
        Books oldBook = booksDao.selectById(idBook);
        if(Objects.isNull(oldBook)) {
            throw new ResourcesServiceException("修改删除位置电子书");
        }
        BeanUtils.copyProperties(book, oldBook);
        return booksDao.updateById(oldBook) > 0;
    }
}
