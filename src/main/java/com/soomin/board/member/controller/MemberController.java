package com.soomin.board.member.controller;

import com.soomin.board.member.dto.MemberDto;
import com.soomin.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * fileName     : MemberController
 * author       : lia
 * date         : 2023/07/07
 * description  : MemberController
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/07       lia          최초 생성
 */
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService; // 생성자 주입

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
     * @param   memberDto   회원 정보
     * @return  회원가입 결과
     */
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDto memberDto) {

        System.out.println("MemberController.save");
        System.out.println("memberDto = " + memberDto);
        memberService.save(memberDto);

        return "login";
    }
}
