package com.varchar6.petcast.domain.proposalandestimate.query.dto;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.ProposalsStatus;
import lombok.Data;

@Data
public class ProposalResponseDTO {
    private int id;
    private String content;
    private int hopeCost;
    private String hopeLocation;
    private String hopeTime;
    private ProposalsStatus status;
    private String createdAt;
    private String updatedAt;
    private boolean active;
}
