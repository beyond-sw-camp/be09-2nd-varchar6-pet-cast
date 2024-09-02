package com.varchar6.petcast.domain.member.command.application.controller;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberDeleteRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRegistRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.domain.member.command.application.service.MemberService;
import com.varchar6.petcast.domain.member.command.application.vo.request.MemberDeleteRequestVO;
import com.varchar6.petcast.domain.member.command.application.vo.request.MemberRegistRequestVO;
import com.varchar6.petcast.domain.member.command.application.vo.request.MemberUpdateRequestVO;
import com.varchar6.petcast.domain.member.command.application.vo.response.MemberDeleteResponseVO;
import com.varchar6.petcast.domain.member.command.application.vo.response.MemberRegistResponseVO;
import com.varchar6.petcast.domain.member.command.application.vo.response.MemberUpdateResponseVO;
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
    public ResponseEntity<MemberRegistResponseVO> signUp(@RequestBody MemberRegistRequestVO newUser){

        MemberRegistRequestDTO memberRegistRequestDTO = modelMapper.map(newUser, MemberRegistRequestDTO.class);

        MemberResponseDTO memberResponseDTO = memberService.registerMember(memberRegistRequestDTO);

        MemberRegistResponseVO responseMember = modelMapper.map(memberResponseDTO, MemberRegistResponseVO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMember);
    }

    @PutMapping("/update-member-status")
    public ResponseEntity<MemberUpdateResponseVO> updateMemberStatus(@RequestBody MemberUpdateRequestVO updateStatus){

        MemberUpdateRequestDTO memberUpdateRequestDTO
                = modelMapper.map(updateStatus, MemberUpdateRequestDTO.class);

        MemberResponseDTO memberResponseDTO = memberService.updateMemberStatus(memberUpdateRequestDTO);

        MemberUpdateResponseVO responseMember = modelMapper.map(memberResponseDTO, MemberUpdateResponseVO.class);

        return ResponseEntity.ok().body(responseMember);
    }

    @PutMapping("/update-password")
    public ResponseEntity<MemberUpdateResponseVO> updateMemberPassword(@RequestBody MemberUpdateRequestVO updateMember){

        MemberUpdateRequestDTO memberUpdateRequestDTO = modelMapper.map(updateMember, MemberUpdateRequestDTO.class);

        MemberResponseDTO memberResponseDTO = memberService.updateMemberPwd(memberUpdateRequestDTO);

        MemberUpdateResponseVO responseMember = modelMapper.map(memberResponseDTO, MemberUpdateResponseVO.class);

        return ResponseEntity.ok().body(responseMember);
    }

    @PutMapping("/delete")
    public ResponseEntity<MemberDeleteResponseVO> deleteMember(@RequestBody MemberDeleteRequestVO deleteMember){

        MemberDeleteRequestDTO memberDeleteRequestDTO = modelMapper.map(deleteMember, MemberDeleteRequestDTO.class);

        MemberResponseDTO memberResponseDTO = memberService.deleteMember(memberDeleteRequestDTO);

        MemberDeleteResponseVO responseMember = modelMapper.map(memberResponseDTO, MemberDeleteResponseVO.class);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseMember);
    }
}
