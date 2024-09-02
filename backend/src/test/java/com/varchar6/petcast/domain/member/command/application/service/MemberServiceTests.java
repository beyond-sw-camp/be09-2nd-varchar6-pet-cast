package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRegistRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTests {

    MemberRegistRequestDTO memberRegistRequestDTO = new MemberRegistRequestDTO();

    @Autowired
    private MemberServiceImpl memberService;

    @Test
    @Transactional
    public void 멤버_생성_확인() {
        memberRegistRequestDTO.setLoginId("testId4");
        memberRegistRequestDTO.setPassword("testPw");
        memberRegistRequestDTO.setName("testName");
        memberRegistRequestDTO.setPhone("testPhone");
        memberRegistRequestDTO.setNickname("testNickname");
        memberRegistRequestDTO.setImage("testImage");
        memberRegistRequestDTO.setIntroduction("testIntroduction");
        MemberResponseDTO memberResponseDTO = memberService.registerMember(memberRegistRequestDTO);
        assertEquals("testId4", memberResponseDTO.getLoginId());

    }



}