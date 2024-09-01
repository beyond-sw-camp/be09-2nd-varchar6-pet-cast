package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService {
    MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO);
}
