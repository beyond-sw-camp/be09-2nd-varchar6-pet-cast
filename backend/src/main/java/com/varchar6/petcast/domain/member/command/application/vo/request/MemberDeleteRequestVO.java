package com.varchar6.petcast.domain.member.command.application.vo.request;

import lombok.Data;

@Data
public class MemberDeleteRequestVO {

    private int id;
    private Boolean active;

}
