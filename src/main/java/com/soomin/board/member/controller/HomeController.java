package com.soomin.board.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
