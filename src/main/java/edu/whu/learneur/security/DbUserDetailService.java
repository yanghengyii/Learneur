package edu.whu.learneur.security;

import edu.whu.learneur.domain.Roles;
import edu.whu.learneur.domain.Users;
import edu.whu.learneur.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DbUserDetailService implements UserDetailsService {
    @Autowired
    IUsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Roles role = usersService.findUserRoleByUsername(username);
        Users user = usersService.findUserByUsername(username);
        if(Objects.isNull(user) || Objects.isNull(role)) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
        return User.builder().username(username)
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .roles(role.getName())
                .build();
    }
}
