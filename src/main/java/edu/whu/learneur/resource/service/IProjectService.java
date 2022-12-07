package edu.whu.learneur.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.entity.Lesson;
import edu.whu.learneur.resource.entity.Project;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProjectService extends IService<Project> {
    List<Project> addProjects(List<Project> projects) throws UserServiceException;

    IPage<Project> findProjectPage(Long knowledgeId, Integer pageNum, Integer pageSize);

    IPage<Project> findAllProjects(Integer pageNum, Integer pageSize);

    Project findById(Long id);
}
