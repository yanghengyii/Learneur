package edu.whu.learneur.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.entity.Knowledge;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IKnowledgeService {
    Knowledge findById(Long id);

    IPage<Knowledge> findKnowledge(Integer pageNum, Integer  pageSize);

    List<Knowledge> findAll();

    void addKnowledge(Knowledge knowledge) throws UserServiceException;
}
