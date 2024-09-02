package com.varchar6.petcast.domain.member.command.application.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegistRequestDTO {
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private String image;
    private String introduction;

}
