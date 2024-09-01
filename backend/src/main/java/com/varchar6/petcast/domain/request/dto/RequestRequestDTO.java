package com.varchar6.petcast.domain.request.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestRequestDTO {
    private int id;
    private String content;
    private int cost;
    private String location;
    private String time;
    private String createdAt;
    private String updatedAt;
    private boolean active;
}
