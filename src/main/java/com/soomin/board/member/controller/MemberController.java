package com.soomin.board.member.controller;

import com.soomin.board.member.dto.MemberDto;
import com.soomin.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
@Controller
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

    /**
     * 회원 조회
     *
     * @param   id          회원 고유번호
     * @param   model       model
     * @return  회원 조회 결과
     */
    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDto memberDto = memberService.findById(id);
        model.addAttribute("member", memberDto);
        return "detail";
    }

    /**
     * 회원 수정 페이지 출력
     *
     * @param   session         httpSession
     * @param   model           model
     * @return  회원 수정 페이지
     */
    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDto memberDto = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDto);
        return "update";
    }

    /**
     * 회원 수정
     *
     * @param   memberDto   회원 정보
     * @return  회원 수정 결과
     */
    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDto memberDto) {
        memberService.update(memberDto);
        return "redirect:/member/" + memberDto.getId();
    }

    /**
     * 회원 삭제
     *
     * @param   id          회원 고유번호
     * @return  회원 삭제 결과
     */
    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    /**
     * 로그아웃
     *
     * @param   session     httpSession
     * @return  로그아웃 결과
     */
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    /**
     * 이메일 중복 체크
     *
     * @param   memberEmail     이메일
     * @return  중복 체크 결과
     */
    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);

        return checkResult;
    }
}
