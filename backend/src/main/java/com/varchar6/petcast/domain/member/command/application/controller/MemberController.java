package com.varchar6.petcast.domain.member.command.application.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberDeleteRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.domain.member.command.application.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "commandMemberController")
@RequestMapping("/api/v1/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberController(MemberService memberService, ModelMapper modelMapper) {
        this.memberService = memberService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<MemberResponseDTO> signUp(@RequestBody MemberRequestDTO newMember){

        MemberRequestDTO memberRequestDTO = modelMapper.map(newMember, MemberRequestDTO.class);

        MemberResponseDTO memberResponseDTO = memberService.registerMember(memberRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDTO);
    }

    @PostMapping("/update-pwd")
    public ResponseEntity<ResponseMessage> updatePwd(@RequestBody MemberUpdateRequestDTO memberUpdateRequestDTO){

        MemberResponseDTO memberResponseDTO = memberService.updatePwd(memberUpdateRequestDTO);

        return ResponseEntity.ok(new ResponseMessage(201, "비밀번호 수정 성공"
                , memberResponseDTO));
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseMessage> deleteMember(@RequestBody MemberDeleteRequestDTO memberDeleteRequestDTO){

        MemberResponseDTO memberResponseDTO = memberService.deleteMember(memberDeleteRequestDTO);

        return ResponseEntity.ok(new ResponseMessage(201, "회원 비활성화"
                , memberResponseDTO));
    }
}
