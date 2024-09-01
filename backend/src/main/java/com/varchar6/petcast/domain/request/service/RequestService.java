package com.varchar6.petcast.domain.request.service;

import com.varchar6.petcast.domain.request.dto.RequestResponseDTO;

import java.util.List;

public interface RequestService {

    // 고객 요청서 목록 조회
    List<RequestResponseDTO> findAllRequestsByMemberId(int memberId);

    // 업체 요청서 목록 조회
    List<RequestResponseDTO> findAllRequestsByCompanyId(int companyId);

    // 상세 조회
    RequestResponseDTO findRequestById(int requestId);
}
