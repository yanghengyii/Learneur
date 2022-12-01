package edu.whu.learneur.security;

import edu.whu.learneur.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtil.isNullOrEmpty(header) || !header.startsWith("Bearer: ")) {
            filterChain.doFilter(request, response);

            return;
        }

        try {
            final String token = header.substring(7);
            Claims claims = jwtTokenUtils.getClaimFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
            if(Objects.isNull(userDetails)) {
                logger.warn("账号 " + userDetails.getUsername() + " 非法登录 - 时间" + LocalDateTime.now());
            }
            if(Objects.nonNull(userDetails) && jwtTokenUtils.validateClaim(claims, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
