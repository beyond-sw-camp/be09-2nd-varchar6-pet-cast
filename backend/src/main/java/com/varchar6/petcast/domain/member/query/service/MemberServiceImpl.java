package com.varchar6.petcast.domain.member.query.service;

import com.varchar6.petcast.domain.member.query.dto.MemberDTO;
import com.varchar6.petcast.domain.member.query.mapper.MemberMapper;
import com.varchar6.petcast.domain.member.query.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service(value="queryMemberService")
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Boolean checkDoubleByLoginId(String loginId) {

        MemberVO memberVO = memberMapper.checkDoubleByLoginId(loginId);

        if(memberVO == null || memberVO.getLoginId() == null) {return false;}

        return true;
    }

    @Override
    public Boolean checkDoubleByNickName(String nickName) {

        MemberVO memberVO = memberMapper.checkDoubleByNickName(nickName);

        if(memberVO == null || memberVO.getNickname() == null) {return false;}

        return true;
    }

    @Override
    public String searchLoginIdByNameAndPhone(String name, String phone) {

        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("phone", phone);
        MemberVO memberVO = memberMapper.searchLoginIdByNameAndPhone(params);

        return memberVO.getLoginId();
    }

    @Override
    public Map<String, Object> checkIdAndPhone(String loginId, String phone) {

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("loginId", loginId);
        resultMap.put("phone", phone);

        MemberVO memberVO = memberMapper.checkIdAndPhone(resultMap);

        Map<String,Object> response = new HashMap<>();

        if(memberVO != null) {
            response.put("canResetPassword", true);
        } else{
            response.put("canResetPassword", false);
            }

        return response;
    }
}
