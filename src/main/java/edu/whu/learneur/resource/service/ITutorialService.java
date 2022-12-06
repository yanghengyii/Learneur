package edu.whu.learneur.resource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.entity.Tutorial;

import java.util.List;

public interface ITutorialService extends IService<Tutorial> {
    List<Tutorial> addTutorial(List<Tutorial> tutorialList) throws UserServiceException;
}
