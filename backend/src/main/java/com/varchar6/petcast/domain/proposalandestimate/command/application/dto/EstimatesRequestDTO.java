package com.varchar6.petcast.domain.proposalandestimate.command.application.dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class EstimatesRequestDTO {
    private int expectedCost;
    private String createdAt;
    private int proposalId;
    private int companyId;

}
