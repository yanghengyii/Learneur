package edu.whu.learneur.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Resources;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

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
}
