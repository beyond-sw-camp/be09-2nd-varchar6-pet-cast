package com.varchar6.petcast.domain.member.query.mapper;

import com.varchar6.petcast.domain.member.query.dto.MemberDTO;
import com.varchar6.petcast.domain.member.query.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberDTO selectMemberByMemberId(int id);

    MemberVO selectMemberByMemberIdWithAuthority(String memberId);
}
