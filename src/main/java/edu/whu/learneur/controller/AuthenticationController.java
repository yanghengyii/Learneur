package edu.whu.learneur.controller;

import edu.whu.learneur.constant.LearneurConst;
import edu.whu.learneur.domain.Users;
import edu.whu.learneur.service.IRolesService;
import edu.whu.learneur.service.IUsersService;
import edu.whu.learneur.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("authenticate")
@Slf4j
public class AuthenticationController {
    // TODO:登录注册界面在此实现, 值的注意的是此处回访过所有用户
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private IUsersService usersService;

    @Resource
    private IRolesService rolesService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody Users users) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword())
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(users.getUsername());
            final String token = jwtTokenUtils.generateToken(userDetails);
            redisTemplate.boundValueOps(users.getUsername()).set(token, LearneurConst.JWT_EXPIRED_TIME, TimeUnit.MILLISECONDS);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            log.warn("用户: " + users.getUsername() + " 认证未通过, 时间 - " + LocalDateTime.now());
            return ResponseEntity.badRequest().body("认证未通过");
        }
    }

//    @PostMapping("/register")
//    @ResponseBody
//    public ResponseEntity<Users> register(@RequestBody User user) {
//
//    }

}
