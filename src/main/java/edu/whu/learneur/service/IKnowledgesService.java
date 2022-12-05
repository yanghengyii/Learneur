package edu.whu.learneur.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Knowledges;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
     * 寻找一个知识结点
     * @param idKnowledge
     * @param pages 页码
     * @param cols  列数
     * @return
     */
    Knowledges findKnowledgeNode(Long idKnowledge, int pages, int cols);

    /**
     * 根据职员获取其知识点/标签
     * @param idResource
     * @return
     */
    List<Knowledges> findKnowledgeByResource(Long idResource);

    /*
     以下是一般的CRUD
     */

    /**
     * @param cols 列数
     * @param pages 页数
     * 查询全部知识点
     * @return
     */
    IPage<Knowledges> findKnowledges(int pages, int cols);

    /**
     * 保存或更新一个知识点
     * @param knowledge
     * @return
     */
    boolean saveOrUpdateKnowledge(Knowledges knowledge);

    /**
     * 删除一个知识点
     * @param idKnowledge
     * @return
     */
    boolean deleteKnowledge(Long idKnowledge);

    /**
     * 删除一批知识点
     * @param idKnowledges
     * @return
     */
    boolean deleteKnowledges(List<Long> idKnowledges);

    /*
    以下是关联关系的相关操作
     */

    /**
     * 添加一批知识点资源关联
     * @param idKnowledge
     * @param idResources
     * @return
     */
    boolean addKnowledgeResourceRelation(Long idKnowledge, List<Long> idResources);

    /**
     * 删除一批知识点资源关联
     * @param idKnowledge
     * @param idResources
     * @return
     */
    boolean deleteKnowledgeResourceRelation(Long idKnowledge, List<Long> idResources);
}
