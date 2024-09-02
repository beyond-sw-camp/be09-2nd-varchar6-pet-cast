package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberDeleteRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRegistRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;

public interface MemberService {

    MemberResponseDTO registerMember(MemberRegistRequestDTO memberRegistRequestDTO);

    MemberResponseDTO deleteMember(MemberDeleteRequestDTO memberDeleteRequestDTO);

    MemberResponseDTO updateMemberPwd(MemberUpdateRequestDTO memberUpdateRequestDTO);

    MemberResponseDTO updateMemberStatus(MemberUpdateRequestDTO memberUpdateRequestDTO);
}
