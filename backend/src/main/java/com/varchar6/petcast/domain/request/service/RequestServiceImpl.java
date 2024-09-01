package com.varchar6.petcast.domain.request.service;

import com.varchar6.petcast.domain.request.aggregate.Request;
import com.varchar6.petcast.domain.request.dto.RequestResponseDTO;
import com.varchar6.petcast.domain.request.repository.RequestMapper;
import com.varchar6.petcast.domain.request.repository.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository,
                              RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }

    // 고객 요청서 목록 조회
    @Override
    public List<RequestResponseDTO> findAllRequestsByMemberId(int memberId) {
        List<Request> requests = requestMapper.findAllRequestsByMemberId(memberId);
        return requests.stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    // 업체 요청서 목록 조회
    @Override
    public List<RequestResponseDTO> findAllRequestsByCompanyId(int companyId) {
        List<Request> requests = requestMapper.findAllRequestsByCompanyId(companyId);
        return requests.stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    // 요청서 상세 조회
    @Override
    public RequestResponseDTO findRequestById(int requestId) {
        Request request = requestMapper.findRequestById(requestId);
        if (request == null) {
            throw new IllegalArgumentException("해당 " + requestId + " 번 요청서를 찾을 수 없습니다.");
        }
        return entityToResponseDTO(request);
    }

    private RequestResponseDTO entityToResponseDTO(Request request) {
        return RequestResponseDTO.builder()
                .id(request.getId())
                .content(request.getContent())
                .cost(request.getCost())
                .location(request.getLocation())
                .time(request.getTime())
                .createdAt(LocalDateTime.now()
                        .format(FORMATTER))
                .updatedAt(LocalDateTime.now()
                        .format(FORMATTER))
                .active(true)
                .build();
    }
}
