package com.varchar6.petcast.domain.proposalandestimate.query.service;


import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Proposals;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.ProposalResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.query.mapper.ProposalsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("queryProposalServiceImpl")
public class ProposalServiceImpl implements ProposalService {
    private final ProposalsMapper proposalsMapper;

    @Autowired
    public ProposalServiceImpl ( ProposalsMapper proposalsMapper ) {
        this.proposalsMapper = proposalsMapper;
    }

    // 고객이 보낸 기획서 목록 조회
    @Override
    public List<ProposalResponseDTO> findAllProposalsByMemberId ( int memberId ) {
        System.out.println ( "memberId: " + memberId );
        List<ProposalResponseDTO> proposals = proposalsMapper.findAllProposalsByMemberId ( memberId );
        return proposals;
    }

    // 업체가 받은 기획서 목록 조회
    @Override
    public List<ProposalResponseDTO> findAll () {
        List<ProposalResponseDTO> allProposals = proposalsMapper.findAll ();
        return allProposals;
    }

    // 기획서 상세 조회
    @Override
    public ProposalResponseDTO findProposalById ( int proposalId ) {
        ProposalResponseDTO aProposal = proposalsMapper.findProposalById ( proposalId );
        return aProposal;
    }


    private ProposalResponseDTO entityToResponseDTO ( Proposals proposals ) {
        return null;
    }
}




