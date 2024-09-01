package com.varchar6.petcast.domain.request.controller;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import com.varchar6.petcast.domain.request.aggregate.Request;
import com.varchar6.petcast.domain.request.dto.RequestResponseDTO;
import com.varchar6.petcast.domain.request.repository.RequestMapper;
import com.varchar6.petcast.domain.request.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {
    private final RequestService requestService;
    private RequestMapper requestMapper;

    @Autowired
    public RequestController(RequestService requestService, RequestMapper requestMapper) {
        this.requestService = requestService;
        this.requestMapper = requestMapper;
    }

    // 고객 요청서 목록 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<RequestResponseDTO>> getRequestsByCustomer(@PathVariable int memberId) {
        List<RequestResponseDTO> requests = requestService.findAllRequestsByMemberId(memberId);
        return ResponseEntity.ok(requests);
    }

    // 업체 요청서 목록 조회
    @GetMapping("/list/{companyId}")
    public ResponseEntity<List<RequestResponseDTO>> getRequestsForCompany(@PathVariable int companyId) {
        List<RequestResponseDTO> requests = requestService.findAllRequestsByCompanyId(companyId);
        return ResponseEntity.ok(requests);
    }

    // 요청서 상세 조회
    @GetMapping("/list/{requestId}")
    public ResponseEntity<RequestResponseDTO> getRequestById(@PathVariable int requestId) {
        RequestResponseDTO request = requestService.findRequestById(requestId);
        return ResponseEntity.ok(request);
    }
}
