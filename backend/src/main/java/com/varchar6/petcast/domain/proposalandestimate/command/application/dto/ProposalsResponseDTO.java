package com.varchar6.petcast.domain.proposalandestimate.command.application.dto;

import lombok.*;


@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProposalsResponseDTO {
    private String content;
    private String hopeLocation;
    private String hopeTime;
    private int hopeCost;

}
