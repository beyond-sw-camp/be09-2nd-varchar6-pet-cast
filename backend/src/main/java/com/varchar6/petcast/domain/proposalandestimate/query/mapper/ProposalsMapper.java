package com.varchar6.petcast.domain.proposalandestimate.query.mapper;

import com.varchar6.petcast.domain.proposalandestimate.query.dto.ProposalResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProposalsMapper {
    List<ProposalResponseDTO> findAllProposalsByMemberId(int memberId);

    ProposalResponseDTO findProposalById( int proposalId);

    List<ProposalResponseDTO> findAll ();
}
