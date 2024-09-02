package com.varchar6.petcast.domain.member.query.service;

import com.varchar6.petcast.domain.member.query.dto.MemberDTO;

import java.util.Map;

public interface MemberService {

    Boolean checkDoubleByLoginId(String loginId);

    Boolean checkDoubleByNickName(String nickName);

    String searchLoginIdByNameAndPhone(String name, String phone);

    Map<String, Object> checkIdAndPhone(String loginId, String phone);

}
