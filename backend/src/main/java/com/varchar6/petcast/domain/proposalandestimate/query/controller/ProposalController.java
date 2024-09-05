package com.varchar6.petcast.domain.proposalandestimate.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.ProposalResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.query.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController("queryProposalController")
@RequestMapping("/api/v1/proposal")

public class ProposalController {

    private final ProposalService proposalService;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public ProposalController ( ProposalService proposalService ) {
        this.proposalService = proposalService;
    }

    // 고객 견적서 목록 조회
    @GetMapping("/list/customer/{memberId}")
    public ResponseEntity<ResponseMessage> findAllProposalsByMemberId ( @PathVariable int memberId ) {
        List<ProposalResponseDTO> proposals
                = proposalService.findAllProposalsByMemberId ( memberId );
        return ResponseEntity.ok ()
                .body ( ResponseMessage.builder ()
                        .httpStatus ( HttpStatus.OK.value () )
                        .message ( "고객가 작성한 기획서 목록 조회 성공" )
                        .result ( proposals )
                        .build () );
    }

    // 업체 기획서 목록 조회
    @GetMapping("/list/company/{companyId}")
    public ResponseEntity<ResponseMessage> findAllProposalsByCompanyId ( @PathVariable int companyId ) {
        List<ProposalResponseDTO> proposals = proposalService.findAll ();
        return ResponseEntity.ok ()
                .body ( ResponseMessage.builder ()
                        .httpStatus ( HttpStatus.OK.value () )
                        .message ( "업체가 받은 기획서 목록 조회 성공" )
                        .result ( proposals )
                        .build () );
    }

    // 기획서 상세 조회
    @GetMapping("/list/detail/{proposalId}")
    public ResponseEntity<ResponseMessage> findProposalById ( @PathVariable int proposalId ) {
        ProposalResponseDTO proposal = proposalService.findProposalById ( proposalId );
        return ResponseEntity.ok ()
                .body ( ResponseMessage.builder ()
                        .httpStatus ( HttpStatus.OK.value () )
                        .message ( "기획서 상세 조회 성공" )
                        .result ( proposal )
                        .build () );
    }
}
