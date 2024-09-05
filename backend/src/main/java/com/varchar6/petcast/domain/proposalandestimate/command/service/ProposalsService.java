package com.varchar6.petcast.domain.proposalandestimate.command.service;


import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsResponseDTO;

import java.util.List;

public interface ProposalsService {


    // 기획서 작성
    ProposalsResponseDTO createProposal(ProposalsRequestDTO proposalRequestDTO);

    // 기획서 삭제
    void deleteProposal(int proposalId);

}
