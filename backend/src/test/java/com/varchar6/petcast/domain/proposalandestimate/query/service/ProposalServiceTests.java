package com.varchar6.petcast.domain.proposalandestimate.query.service;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Proposals;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.ProposalResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProposalServiceTests {

    @Autowired
    ProposalService proposalService;

    @Test
    void 고객_기획서_목록_조회_테스트 () {
        int memberId = 5;
        List<ProposalResponseDTO> findProposals = proposalService.findAllProposalsByMemberId (memberId);
         assertEquals ( 2, findProposals.size());

    }

    @Test
    void 업체_기획서_목록_조회_성공 () {
        List<ProposalResponseDTO> findProposals = proposalService.findAll();
        assertEquals ( 34, findProposals.size() );
    }

    @Test
    void 기획서_상세_조회 () {
        int id = 1;
        ProposalResponseDTO aProposal = proposalService.findProposalById ( id );
        assertEquals ( aProposal.getId(), id );
    }
}