package com.varchar6.petcast.domain.member.query.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTests {

    @Autowired
    private MemberService memberService;


    @Test
    public void 멤버아이디로_멤버_조회() {
        int input_number = 1;

        int memberId = memberService.getMemberInformationById(input_number).getId();
        Assertions.assertTrue((input_number == memberId));
    }

    @Test
    @Transactional
    public void 멤버생성_및_조회() {
    }


}
