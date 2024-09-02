package com.varchar6.petcast.domain.member.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.member.query.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController(value = "queryMemberController")
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("{memberId}")
    public ResponseEntity<ResponseMessage> getMember(
            @PathVariable("memberId") Integer memberId
    ) {
        return ResponseEntity.ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.CREATED.value())
                                .message("Login completed")
                                .result(memberService.getMemberInformationById(memberId))
                                .build()
                );
    }

}
