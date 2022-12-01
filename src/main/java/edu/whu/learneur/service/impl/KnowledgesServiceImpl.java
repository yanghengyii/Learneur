package edu.whu.learneur.service.impl;

import edu.whu.learneur.domain.Knowledges;
import edu.whu.learneur.dao.KnowledgesDao;
import edu.whu.learneur.service.IKnowledgesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 知识点 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
public class KnowledgesServiceImpl extends ServiceImpl<KnowledgesDao, Knowledges> implements IKnowledgesService {

}
