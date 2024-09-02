package com.varchar6.petcast.domain.member.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberDeleteRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRegistRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.domain.member.command.application.service.MemberService;
import com.varchar6.petcast.domain.member.command.application.vo.request.MemberRegistRequestVO;
import com.varchar6.petcast.domain.member.command.application.vo.response.MemberRegistResponseVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MemberController.class)
class MemberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signUp_ShouldReturnCreatedResponse() throws Exception {
        // Arrange
        MemberRegistRequestVO request = new MemberRegistRequestVO();
        MemberRegistRequestDTO memberRegistRequestDTO = new MemberRegistRequestDTO();
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        MemberRegistResponseVO response = new MemberRegistResponseVO();

        when(memberService.registerMember(any(MemberRegistRequestDTO.class)))
                .thenReturn(memberResponseDTO);
        when(objectMapper.convertValue(memberResponseDTO, MemberRegistResponseVO.class))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/v1/members/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.someField").value("expectedValue")); // Adjust as per actual field names and values
    }

    @Test
    void updateMemberPwd_ShouldReturnOkResponse() throws Exception {
        // Arrange
        MemberUpdateRequestDTO request = new MemberUpdateRequestDTO();
        MemberResponseDTO responseDTO = new MemberResponseDTO();

        when(memberService.updateMemberPwd(any(MemberUpdateRequestDTO.class)))
                .thenReturn(responseDTO);

        // Act & Assert
        mockMvc.perform(put("/api/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("회원 비활성화 성공")); // Adjust as per actual response message
    }

    @Test
    void deleteMember_ShouldReturnOkResponse() throws Exception {
        // Arrange
        MemberDeleteRequestDTO request = new MemberDeleteRequestDTO();
        MemberResponseDTO responseDTO = new MemberResponseDTO();

        when(memberService.deleteMember(any(MemberDeleteRequestDTO.class)))
                .thenReturn(responseDTO);

        // Act & Assert
        mockMvc.perform(put("/api/v1/members/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("회원 비활성화 성공")); // Adjust as per actual response message
    }
}
