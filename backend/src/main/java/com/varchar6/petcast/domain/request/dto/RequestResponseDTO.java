package com.varchar6.petcast.domain.request.dto;

import lombok.*;

@Builder
@Data
public class RequestResponseDTO {
    private int id;
    private String content;
    private int cost;
    private String location;
    private String time;
    private String createdAt;
    private String updatedAt;
    private boolean active;
}
