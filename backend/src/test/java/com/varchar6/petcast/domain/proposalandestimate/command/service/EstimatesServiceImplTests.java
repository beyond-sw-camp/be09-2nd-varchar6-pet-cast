package com.varchar6.petcast.domain.proposalandestimate.command.service;

import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Estimates;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.EstimatesStatus;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Proposals;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.repository.EstimatesRepository;
import com.varchar6.petcast.domain.proposalandestimate.command.service.EstimatesService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EstimatesServiceImplTests {

    private EstimatesService estimatesService;
    private EstimatesRepository estimatesRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public EstimatesServiceImplTests ( EstimatesService estimatesService,  EstimatesRepository estimatesRepository ) {
        this.estimatesService = estimatesService;
        this.estimatesRepository = estimatesRepository;
    }

    @Test
    @Transactional
    void 견적서_작성_테스트 () {
        EstimatesRequestDTO estimatesRequestDTO = new EstimatesRequestDTO();

        estimatesRequestDTO.setCreatedAt ( LocalDateTime.now ().format ( FORMATTER ) );
        estimatesRequestDTO.setCompanyId ( 1 );
        estimatesRequestDTO.setProposalId ( 1 );
        estimatesRequestDTO.setExpectedCost ( 100000 );
        int result = estimatesService.createEstimate (estimatesRequestDTO);

        assertEquals ( 1,result);

    }

    @Test
    @Transactional
    void 견적서_삭제_테스트 () {
        int id = 1;
        estimatesRepository.deleteById ( id );
    }

    @Test
    @Transactional
    void 견적서_수락_테스트 () {
        Estimates estimates = Estimates.builder ()
                .id ( 1 ).status ( EstimatesStatus.CONFIRMED ).build ();
        System.out.println (estimatesRepository.save ( estimates ));
    }

    @Test
    @Transactional
    void 견적서_거절_테스트 () {
        Estimates estimates = Estimates.builder ()
                                    .id ( 1 ).status ( EstimatesStatus.REJECTED ).build ();
        System.out.println (estimatesRepository.save ( estimates ));
    }
}