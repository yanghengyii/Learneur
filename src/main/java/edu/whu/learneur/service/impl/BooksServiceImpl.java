package edu.whu.learneur.service.impl;

import edu.whu.learneur.domain.Books;
import edu.whu.learneur.dao.BooksDao;
import edu.whu.learneur.service.IBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 书籍 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
public class BooksServiceImpl extends ServiceImpl<BooksDao, Books> implements IBooksService {

}
