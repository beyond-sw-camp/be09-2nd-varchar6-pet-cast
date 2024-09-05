package com.varchar6.petcast.domain.proposalandestimate.command.application.dto;


import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.ProposalsStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProposalsRequestDTO {
    private int id;
    private String content;
    private String hopeLocation;
    private String hopeTime;
    private int hopeCost;
    private ProposalsStatus status;
    private String createdAt;
    private String updatedAt;
    private boolean active;
    private int memberId;

}
