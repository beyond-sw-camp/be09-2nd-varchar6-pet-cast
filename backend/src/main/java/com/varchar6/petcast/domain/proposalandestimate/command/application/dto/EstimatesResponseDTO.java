package com.varchar6.petcast.domain.proposalandestimate.command.application.dto;


import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.EstimatesStatus;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EstimatesResponseDTO {
    private int id;
    private int expectedCost;
    private EstimatesStatus status;
    private String createdAt;
    private String updatedAt;
    private boolean active;
    private int proposalId;
    private int companyId;


}
