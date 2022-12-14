package edu.whu.learneur.controller;

import edu.whu.learneur.constant.LearneurConst;
import edu.whu.learneur.domain.Users;
import edu.whu.learneur.dto.ForgetPasswordDTO;
import edu.whu.learneur.dto.RegisterUserDTO;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.service.IEMailService;
import edu.whu.learneur.service.IRolesService;
import edu.whu.learneur.service.IUsersService;
import edu.whu.learneur.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.annotation.security.PermitAll;
import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("authenticate")
@Slf4j
public class AuthenticationController {
    // TODO:登录注册界面在此实现, 值的注意的是此处会访过所有用户
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

    @Resource
    private IEMailService mailService;

    /**
     * 登录操作
     * @param users 用户对象
     * @return
     */
    @PostMapping("/login")
    @PermitAll
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody Users users) {
        try {
            System.out.println("---------------------0");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword())
            );
            System.out.println("---------------------1");
            final UserDetails userDetails = userDetailsService.loadUserByUsername(users.getUsername());
            final String token = jwtTokenUtils.generateToken(userDetails);
            System.out.println("---------------------2");
            redisTemplate.boundValueOps(users.getUsername()).set(token, LearneurConst.JWT_EXPIRED_TIME, TimeUnit.MILLISECONDS);

            usersService.updateOnlineStatusByUsername(users.getUsername());

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            log.warn("用户: " + users.getEmail() + " 认证未通过, 时间 - " + LocalDateTime.now());
            return ResponseEntity.badRequest().body("认证未通过");
        }
    }

    /**
     * 发送验证码
     * @param email 注册时填入邮件
     * @return      操作结果
     * @throws MessagingException
     */
    @GetMapping("/get-email-code")
    @PermitAll
    @CrossOrigin
    public ResponseEntity<String> getEmailCode(@RequestParam(value = "email") String email) throws MessagingException {
        Users users = usersService.findUserByEmail(email);
        if(Objects.nonNull(users)) {
            throw new UserServiceException("该邮箱已被注册!");
        }
        boolean res = mailService.sendEmailCode(email); // NOTE: 此处redis已经记录下对应地验证码(email, code)
        if(res) {
            return ResponseEntity.ok("验证码发送成功, 请前往" + email + "获取");
        }
        else {
            return ResponseEntity.ok("邮件发送失败, 请检查邮箱地址");
        }
    }

    /**
     * 发送忘记密码的验证码
     * @param email 注册时填入邮件
     * @return      操作结果
     * @throws MessagingException
     */
    @PostMapping("/get-forget-password-code")
    @CrossOrigin
    private ResponseEntity<String> getForgetPasswordCode(@RequestParam(value = "email")String email) throws MessagingException {
        Users user = usersService.findUserByEmail(email);
        if(Objects.isNull(user)) {
            throw new UserServiceException("用户不存在!");
        }
        boolean res = mailService.sendForgetPasswordEmail(email);  // 此处将数据保存在redis, (code, email)
        if(res) {
            return ResponseEntity.ok("验证码发送成功, 请前往" + email + "获取");
        }
        else {
            return ResponseEntity.ok("邮件发送失败, 请检查邮箱地址");
        }
    }

    /**
     * 注册操作
     * @param user  注册传输对象, 包含字段: username, password, email, code
     * @return
     */
    @PostMapping("/register")
    @PermitAll
    @CrossOrigin
    public ResponseEntity<Boolean> register(@RequestBody RegisterUserDTO user) {
        boolean res = usersService.register(user);
        return ResponseEntity.ok(res);
    }

    /**
     * 忘记密码操作
     * @param user  忘记密码传输对象, 包含字段: code, password
     * @return
     */
    @PostMapping("/forget-password")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Boolean> forgetPassword(@RequestBody ForgetPasswordDTO user) {
        boolean res = usersService.forgetPassword(user);
        return ResponseEntity.ok(res);
    }


}
