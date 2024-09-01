package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleMember;
import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleType;
import com.varchar6.petcast.domain.member.command.domain.repository.MemberRepository;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.domain.member.command.domain.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@Service(value="commandMemberServiceImpl")
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder,
                             RoleRepository roleRepository) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO) {

        // 비밀번호 암호화
        memberRequestDTO.setPassword(bCryptPasswordEncoder.encode(memberRequestDTO.getPassword()));

        // Member 생성
        Member member = memberRepository.save(requestDTOToEntity(memberRequestDTO));

        // 회원 권한 부여
        roleRepository.save(RoleMember.builder()
                .memberId(member.getId())
                .roleId(RoleType.CUSTOMER.getRoleId())
                .build());

        return entityToResponseDTO(member);
    }

    public static Member requestDTOToEntity(MemberRequestDTO memberRequestDTO) {
        return Member.builder()
                .loginId(memberRequestDTO.getLoginId())
                .password(memberRequestDTO.getPassword())
                .name(memberRequestDTO.getName())
                .phone(memberRequestDTO.getPhone())
                .nickname(memberRequestDTO.getNickname())
                .image(memberRequestDTO.getImage())
                .createdAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .updatedAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .active(true)
                .introduction(memberRequestDTO.getIntroduction())
                .build();
    }

    public static MemberResponseDTO entityToResponseDTO(Member member) {
        return MemberResponseDTO.builder()
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .name(member.getName())
                .phone(member.getNickname())
                .nickname(member.getNickname())
                .image(member.getImage())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .active(member.isActive())
                .introduction(member.getIntroduction())
                .build();
    }
}
