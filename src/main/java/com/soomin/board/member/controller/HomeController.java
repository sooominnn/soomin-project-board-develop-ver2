package com.soomin.board.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * fileName     : HomeController
 * author       : lia
 * date         : 2023/07/07
 * description  : HomeController
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/07       lia          최초 생성
 */
@RestController
@RequiredArgsConstructor
public class HomeController {

    /**
     * 회원가입 페이지 출력 요청
     *
     * @return 회원가입 페이지
     */
    @GetMapping("/member/save")
    public String saveForm() {

        return "save";
    }

    /**
     * 회원가입
     *
     * @param   memberEmail     이메일
     * @param   memberPassword  비밀번호
     * @param   memberName      이름
     * @return  회원가입 결과
     */
    @PostMapping("/member/save")
    public String save(@RequestParam("memberEmail") String memberEmail,
                       @RequestParam("memberPassword") String memberPassword,
                       @RequestParam("memberName") String memberName) {
        System.out.println("MemberController.save");
        System.out.println("memberEmail = " + memberEmail + ", memberPassword = " + memberPassword + ", memberName = " + memberName);

        return "index";
    }
}
