package com.varchar6.petcast.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.varchar6.petcast.domain.member.query.service.MemberAuthenticationService;
import com.varchar6.petcast.domain.member.query.service.MemberService;
import com.varchar6.petcast.domain.member.query.vo.LoginRequestVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final MemberAuthenticationService memberAuthenticationService;
    private final Environment environment;

    public AuthenticationFilter(AuthenticationManager authenticationManager, MemberAuthenticationService memberAuthenticationService, Environment environment) {
        super(authenticationManager);
        this.environment = environment;
        this.memberAuthenticationService = memberAuthenticationService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestVO credentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequestVO.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getLoginId(), credentials.getPassword(), new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
