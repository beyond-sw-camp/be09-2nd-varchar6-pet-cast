package com.varchar6.petcast.domain.member.query.mapper;

import com.varchar6.petcast.domain.member.query.dto.MemberDTO;
import com.varchar6.petcast.domain.member.query.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberMapper {

    MemberVO selectMemberByMemberIdWithAuthority(String memberId);

    MemberVO checkDoubleByLoginId(String loginId);

    MemberVO checkDoubleByNickName(String nickName);

    MemberVO searchLoginIdByNameAndPhone(Map<String, String> params);

    MemberVO checkIdAndPhone(Map<String, Object> resultMap);
}
