package com.varchar6.petcast.domain.proposalandestimate.command.service;


import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Estimates;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.EstimatesStatus;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.repository.EstimatesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service("commandEstimatesServiceImpl")
@Slf4j
public class EstimatesServiceImpl implements EstimatesService {

    private final EstimatesRepository estimatesRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);


    @Autowired
    public EstimatesServiceImpl ( EstimatesRepository estimatesRepository ) {
        this.estimatesRepository = estimatesRepository;
    }

    // 견적서 작성
    @Override
    @Transactional
    public int createEstimate( EstimatesRequestDTO estimatesRequestDTO) {
        int result = 0;
        Estimates estimates = Estimates.builder()
                .status ( EstimatesStatus.SENT )
                .expectedCost(estimatesRequestDTO.getExpectedCost())
                .createdAt(estimatesRequestDTO.getCreatedAt ())
                .updatedAt(LocalDateTime.now().format(FORMATTER))
                .active(true)
                .companyId(estimatesRequestDTO.getCompanyId())
                .proposalId (estimatesRequestDTO.getProposalId())
                .build();

        try{
            estimatesRepository.save(estimates);
            result++;
        }catch (Exception e) {
            throw new RuntimeException ("견적서 작성 실패!",e);
        }

        return result;

    }

    // 견적서 삭제
    @Override
    @Transactional
    public void deleteEstimate(int estimateId) {
        if(estimatesRepository.existsById(estimateId)) {
            estimatesRepository.deleteById(estimateId);
        } else {
            throw new IllegalArgumentException("해당 견적서를 찾을 수 없습니다.");
        }
    }

    // 견적서 수락
    @Override
    @Transactional
    public EstimatesResponseDTO acceptEstimate(int estimateId) {
        Estimates estimates = estimatesRepository.findById(estimateId).orElseThrow(IllegalArgumentException::new);

        estimates.accept();
        estimates = estimatesRepository.save(estimates);
        return entityToResponseDTO(estimates);
    }

    // 견적서 거절
    @Override
    @Transactional
    public EstimatesResponseDTO rejectEstimate(int estimateId) {
        Estimates estimates = estimatesRepository.findById(estimateId).orElseThrow(IllegalArgumentException::new);

        estimates.reject();
        estimates = estimatesRepository.save(estimates);
        return entityToResponseDTO(estimates);
    }

    private EstimatesResponseDTO entityToResponseDTO(Estimates estimates) {
        return EstimatesResponseDTO.builder()
                .id ( estimates.getId () )
                .expectedCost(estimates.getExpectedCost())
                .status ( estimates.getStatus () )
                .createdAt ( estimates.getCreatedAt () )
                .updatedAt ( estimates.getUpdatedAt () )
                .active ( estimates.isActive () )
                .companyId ( estimates.getCompanyId () )
                .proposalId ( estimates.getProposalId () )
                .build();
    }

}


