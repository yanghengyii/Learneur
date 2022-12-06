package edu.whu.learneur.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Knowledge;
import edu.whu.learneur.exception.UserServiceException;
import org.springframework.stereotype.Service;

public interface IKnowledgeService {
    Knowledge findById(Long id);

    IPage<Knowledge> findKnowledge(Integer pageNum, Integer  pageSize);

    void addKnowledge(Knowledge knowledge) throws UserServiceException;
}
