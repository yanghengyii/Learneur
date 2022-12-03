package edu.whu.learneur.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Users;
import edu.whu.learneur.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IUsersService usersService;

    @GetMapping("/users/all")
    public ResponseEntity<IPage<Users>> findAllUsers(
            @RequestParam(value = "pages", defaultValue = "0") int pages,
            @RequestParam(value = "cols", defaultValue = "15") int cols
    ) {
        return ResponseEntity.ok(usersService.findUsers(false, pages, cols));
    }

    @GetMapping("/users/all-online")
    public ResponseEntity<IPage<Users>> findAllUsersOnline(
            @RequestParam(value = "pages", defaultValue = "0") int pages,
            @RequestParam(value = "cols", defaultValue = "15") int cols
    ) {
        return ResponseEntity.ok(usersService.findUsers(true, pages, cols));
    }

    @DeleteMapping("/users/delete-user-by-batches")
    public ResponseEntity<Boolean> deleteUserByBatches(List<Long> idUsers) {
        return ResponseEntity.ok(usersService.deleteUsersByBatches(idUsers));
    }
}
