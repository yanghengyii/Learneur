package edu.whu.learneur.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Notes;
import edu.whu.learneur.domain.Users;
import edu.whu.learneur.dto.ChangeEmailDTO;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.service.INotesService;
import edu.whu.learneur.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.Objects;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @Autowired
    private INotesService notesService;

    @PatchMapping("/update-userinfo")
    @PreAuthorize("hasAnyAuthority('admin') or #username == authentication.name")
    public ResponseEntity<Boolean> updateUserinfo(String username, Users user) {
        boolean res = usersService.updateUserInfoByUsername(username, user);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get-user/{username}")
    @PreAuthorize("hasAnyAuthority('user', 'admin') or #username == authentication.name")
    public ResponseEntity<Users> getUser(@PathVariable String username) {
        return ResponseEntity.ok(usersService.findUserByUsername(username));
    }

    @DeleteMapping("/delete-user/{username}")
    @PreAuthorize("hasAnyAuthority('admin') or #username == authentication.name")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String username) {
        return ResponseEntity.ok(usersService.deleteUsersByUsername(username));
    }

    @PatchMapping("/change-email")
    @PreAuthorize("hasAnyAuthority('admin') or #changeEmailDTO.username == authentication.name")
    public ResponseEntity<Boolean> changeEmail(@RequestBody ChangeEmailDTO changeEmailDTO) {
        return ResponseEntity.ok(usersService.changeEmail(changeEmailDTO));
    }

    @GetMapping("/all-articles/{username}")
    @PermitAll
    public ResponseEntity<IPage<Notes>> findNotesByUser(
            @PathVariable String username,
            @RequestParam(value = "pages", defaultValue = "0") int pages,
            @RequestParam(value = "cols", defaultValue = "15") int cols
    ) {
        Users user = usersService.findUserByUsername(username);
        if(Objects.isNull(user)) {
            throw new UserServiceException("未知用户");
        }
        return ResponseEntity.ok(notesService.findNotesByResources(user.getUserId(), pages, cols));
    }


}

