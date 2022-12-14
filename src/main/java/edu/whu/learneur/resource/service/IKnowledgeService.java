package edu.whu.learneur.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.entity.Knowledge;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IKnowledgeService extends IService<Knowledge> {
    Knowledge findById(Long id);

    IPage<Knowledge> findKnowledge(Integer pageNum, Integer  pageSize);

    List<Knowledge> findAll();

    void addKnowledge(Knowledge knowledge) throws UserServiceException;

    IPage<Knowledge> findTop(Integer pageNum, Integer  pageSize);
}
