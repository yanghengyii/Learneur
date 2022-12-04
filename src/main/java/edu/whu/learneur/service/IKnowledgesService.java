package edu.whu.learneur.service;

import edu.whu.learneur.domain.Knowledges;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 知识点 服务类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
public interface IKnowledgesService extends IService<Knowledges> {
    /*
      附注, 知识点的CRUD由图数据库完成, 此处仅实现结点与资源的关系查询
     */

    /**
     * 此处需要与图数据库结点主键对其
     */

    /**
     * 寻找一个指示结点
     * @param idKnowledge
     * @param pages 页码
     * @param cols  列数
     * @return
     */
    Knowledges findKnowledgeNode(Long idKnowledge, int pages, int cols);
}
