package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberDeleteRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.application.vo.response.MemberUpdateResponseVO;
import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleMember;
import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleType;
import com.varchar6.petcast.domain.member.command.domain.repository.MemberRepository;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRegistRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.domain.member.command.domain.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;


@Slf4j
@Service(value="commandMemberServiceImpl")
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);
    private final ModelMapper modelMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder,
                             RoleRepository roleRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public MemberResponseDTO registerMember(MemberRegistRequestDTO memberRegistRequestDTO) {

        // 비밀번호 암호화
        memberRegistRequestDTO.setPassword(bCryptPasswordEncoder.encode(memberRegistRequestDTO.getPassword()));

        // Member 생성
        Member member = memberRepository.save(requestDTOToEntity(memberRegistRequestDTO));

        // 회원 권한 부여
        roleRepository.save(RoleMember.builder()
                .memberId(member.getId())
                .roleId(RoleType.CUSTOMER.getRoleId())
                .build());

        return entityToResponseDTO(member);
    }



    @Override
    @Transactional
    public MemberResponseDTO deleteMember(MemberDeleteRequestDTO memberDeleteRequestDTO) {
        Member member = memberRepository.findById(memberDeleteRequestDTO.getId()).orElseThrow();
        member.setActive(memberDeleteRequestDTO.getActive());
        member.setUpdatedAt(LocalDateTime.now().format(FORMATTER));

        MemberResponseDTO responseDTO = modelMapper.map(member, MemberResponseDTO.class);
        return responseDTO;
    }

    @Override
    @Transactional
    public MemberResponseDTO updateMemberPwd(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        Member member = memberRepository.findById(memberUpdateRequestDTO.getId()).orElseThrow();
        member.setPassword(memberUpdateRequestDTO.getPassword());
        member.setUpdatedAt(LocalDateTime.now().format(FORMATTER));

        MemberResponseDTO responseDTO = modelMapper.map(member, MemberResponseDTO.class);

        return responseDTO;
    }

    @Override
    @Transactional
    public MemberResponseDTO updateMemberStatus(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        Member member = memberRepository.findById(memberUpdateRequestDTO.getId()).orElseThrow();
        member.setActive(false);

        MemberResponseDTO responseDTO = modelMapper.map(member,MemberResponseDTO.class);

        return responseDTO;
    }


    public static Member requestDTOToEntity(MemberRegistRequestDTO memberRegistRequestDTO) {
        return Member.builder()
                .loginId(memberRegistRequestDTO.getLoginId())
                .password(memberRegistRequestDTO.getPassword())
                .name(memberRegistRequestDTO.getName())
                .phone(memberRegistRequestDTO.getPhone())
                .nickname(memberRegistRequestDTO.getNickname())
                .image(memberRegistRequestDTO.getImage())
                .createdAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .updatedAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .active(true)
                .introduction(memberRegistRequestDTO.getIntroduction())
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
