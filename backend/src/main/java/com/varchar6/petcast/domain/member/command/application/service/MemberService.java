package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberDeleteRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO);

    MemberResponseDTO updatePwd(MemberUpdateRequestDTO memberUpdateRequestDTO);

    MemberResponseDTO deleteMember(MemberDeleteRequestDTO memberDeleteRequestDTO);
}
