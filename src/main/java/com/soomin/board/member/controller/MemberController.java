package com.soomin.board.member.controller;

import com.soomin.board.member.dto.MemberDto;
import com.soomin.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    /**
     * 로그인 페이지 출력
     *
     * @return 로그인 페이지
     */
    @GetMapping("/member/login")
    public String loginForm() {

        return "login";
    }

    /**
     * 로그인
     *
     * @param   memberDto   회원 정보
     * @param   session     httpSession
     * @return  로그인 결과
     */
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session) {

        MemberDto loginResult = memberService.login(memberDto);
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            // 로그인 실패
            return "login";
        }
    }

    /**
     * 회원 목록 페이지 출력
     *
     * @param   model           model
     * @return  회원 목록 페이지
     */
    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDto> memberDtoList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model 사용
        model.addAttribute("memberList", memberDtoList);
        return "list";
    }

}
