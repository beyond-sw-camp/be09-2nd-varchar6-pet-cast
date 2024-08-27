package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTests {
    @Autowired
    private MemberService memberService;



    @Test
    @Transactional
    public void 멤버_생성_확인() {
        MemberRequestDTO memberRequestDTO =
                MemberRequestDTO.builder()
                        .loginId("testId4")
                        .password("testPw")
                        .name("testName")
                        .phone("testPhone")
                        .nickname("testNickname")
                        .image("testImage")
                        .introduction("testIntroduction")
                        .build();
        MemberResponseDTO memberResponseDTO = memberService.registerMember(memberRequestDTO);
        assertNotNull(memberResponseDTO);

    }



}