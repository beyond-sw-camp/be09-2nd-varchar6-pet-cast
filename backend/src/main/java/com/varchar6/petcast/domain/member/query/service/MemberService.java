package com.varchar6.petcast.domain.member.query.service;

import com.varchar6.petcast.domain.member.query.dto.MemberDTO;

public interface MemberService {
    MemberDTO getMemberInformationById(int memberId);
}
