package edu.whu.learneur.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Notes;
import edu.whu.learneur.service.INotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * <p>
 * 学习笔记 前端控制器
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@RestController
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private INotesService notesService;


}

