package com.varchar6.petcast.domain.member.command.application.vo.response;

import lombok.Data;

@Data
public class MemberDeleteResponseVO {

    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private String image;
    private String createdAt;
    private String updatedAt;
    private Boolean active;
    private String introduction;

}
