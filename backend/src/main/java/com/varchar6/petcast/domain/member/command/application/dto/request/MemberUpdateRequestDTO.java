package com.varchar6.petcast.domain.member.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateRequestDTO {

    private int id;
    private String password;

}
