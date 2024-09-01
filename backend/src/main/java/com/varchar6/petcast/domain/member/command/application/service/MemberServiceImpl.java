package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberDeleteRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import com.varchar6.petcast.domain.member.command.domain.repository.MemberRepository;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@Service(value="commandMemberServiceImpl")
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);
    private final ModelMapper modelMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO) {
        log.info("memberRequestDTO.image: {}", memberRequestDTO.getImage());
        memberRequestDTO.setPassword(bCryptPasswordEncoder.encode(memberRequestDTO.getPassword()));

        return entityToResponseDTO(memberRepository.save(requestDTOToEntity(memberRequestDTO)));
    }

    @Override
    @Transactional
    public MemberResponseDTO updatePwd(MemberUpdateRequestDTO memberUpdateRequestDTO) {

        Member member = memberRepository.findById(memberUpdateRequestDTO.getId()).orElseThrow();
        member.setPassword(memberUpdateRequestDTO.getPassword());

        MemberResponseDTO responseDTO = modelMapper.map(member, MemberResponseDTO.class);
        return responseDTO;
    }

    @Override
    @Transactional
    public MemberResponseDTO deleteMember(MemberDeleteRequestDTO memberDeleteRequestDTO) {

        Member member = memberRepository.findById(memberDeleteRequestDTO.getId()).orElseThrow();
        member.setActive(memberDeleteRequestDTO.getActive());

        MemberResponseDTO responseDTO = modelMapper.map(member, MemberResponseDTO.class);
        return responseDTO;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
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
