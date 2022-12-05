package edu.whu.learneur.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.domain.Resources;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 * <p>
 *     值得注意一点, 此处为资源的抽象搜索, 故而具体实现
 *     仍旧为book, lesson, project底层接口的实现
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
public interface IResourcesService extends IService<Resources> {
    /**
     * 根据类型寻找对应的资源
     * @param requiredType  类型
     * @param pages         页码
     * @param cols          行数
     * @return
     * @param <T>           泛型
     */
    <T> IPage<T> findResources(Class<T> requiredType, int pages, int cols);

    /**
     * 根据主键查找资源
     * @param idSpecify    资源id
     * @param requiredType 资源类型
     * @return
     * @param <T>
     */
    <T> T findResourceByIdSpecify(Long idSpecify, Class<T> requiredType);

    /**
     * 保存资源
     * @param resource      保存资源
     * @return              操作结果
     */
    boolean saveOrUpdateResource(Object resource);

    /**
     * 保存一组资源
     * @param resources     资源组
     * @return
     */
    boolean saveOrUpdateResources(List<Object> resources);

    /**
     * 删除资源
     * @param idSpecific    具体资源id
     * @param type          资源类型
     * @return
     */
    boolean deleteResource(Long idSpecific, int type);

    /**
     * 同时删除一批资源
     * @param idSpecifics   资源ids
     * @param type          类型
     * @return
     */
    boolean deleteResources(List<Long> idSpecifics, int type);

    /**
     * 根据主键删除数据
     * @param idResources
     * @return
     */
    boolean deleteResourcesByIds(List<Long> idResources);

}
