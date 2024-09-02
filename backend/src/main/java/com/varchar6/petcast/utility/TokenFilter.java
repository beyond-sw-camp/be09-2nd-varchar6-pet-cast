package com.varchar6.petcast.utility;

import com.varchar6.petcast.security.CustomUser;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.io.IOException;

@Slf4j
public class TokenFilter implements Filter {
    private final JwtUtil jwtUtil;

    public TokenFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = ((HttpServletRequest) servletRequest);

                String token = httpServletRequest.getHeader("Authorization")
                .replace("Bearer ", "");

        Authentication authentication = jwtUtil.getAuthentication(token);
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        httpServletRequest.setAttribute("memberId", customUser.getId());
        httpServletRequest.setAttribute("memberLoginId", customUser.getUsername());
        httpServletRequest.setAttribute("memberPhone", customUser.getPhone());
        httpServletRequest.setAttribute("memberNickname", customUser.getNickname());
        httpServletRequest.setAttribute("image", customUser.getImage());
        httpServletRequest.setAttribute("created", customUser.getCreatedAt());
        httpServletRequest.setAttribute("updated", customUser.getUpdatedAt());
        httpServletRequest.setAttribute("active", customUser.isActive());
        httpServletRequest.setAttribute("introduction", customUser.getIntroduction());
        httpServletRequest.setAttribute("authorities", customUser.getAuthorities());

        filterChain.doFilter(servletRequest, servletResponse);


    }
}
