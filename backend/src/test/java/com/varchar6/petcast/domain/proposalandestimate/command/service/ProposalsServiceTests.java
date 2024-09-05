package com.varchar6.petcast.domain.proposalandestimate.command.service;

import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Proposals;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.repository.ProposalsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProposalsServiceTests {

    private final ProposalsService proposalsService;
    private ProposalsRequestDTO proposalsRequestDTO;
    private ProposalsResponseDTO proposalsResponseDTO;
    private ProposalsRepository proposalsRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public ProposalsServiceTests ( ProposalsService proposalsService, ProposalsRequestDTO proposalsRequestDTO, ProposalsResponseDTO proposalsResponseDTO, ProposalsRepository proposalsRepository ) {
        this.proposalsService = proposalsService;
        this.proposalsRequestDTO = proposalsRequestDTO;
        this.proposalsResponseDTO = proposalsResponseDTO;
        this.proposalsRepository = proposalsRepository;
    }


    @Test
    @Transactional
    void 기획서_작성_테스트 () {
        proposalsRequestDTO.setContent ( "testContent" );
        proposalsRequestDTO.setHopeLocation ( "seoul" );
        proposalsRequestDTO.setHopeTime ( "2024-09-10T18:26:45" );
        proposalsRequestDTO.setHopeCost ( 50000 );
        ProposalsResponseDTO result = proposalsService.createProposal (proposalsRequestDTO);

        assertEquals ( "testContent",result.getContent());

    }

    @Test
    @Transactional
    void 기획서_삭제_테스트 () {
        int id = 1;
        proposalsService.deleteProposal ( id );

        Proposals deleleProposal = proposalsRepository.findById(id).orElse ( null );
        assertNull ( deleleProposal );
    }
}