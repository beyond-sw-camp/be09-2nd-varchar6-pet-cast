package com.varchar6.petcast.domain.member.query.service;

import com.varchar6.petcast.domain.member.query.dto.MemberDTO;
import com.varchar6.petcast.domain.member.query.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value="queryMemberService")
public class MemberService {
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public MemberDTO getMemberInformationById(int memberId) {
        return memberMapper.selectMemberByMemberId(memberId);
    }

}
