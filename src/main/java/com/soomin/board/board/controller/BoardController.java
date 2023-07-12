package com.soomin.board.board.controller;

import com.soomin.board.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * fileName     : BoardController
 * author       : lia
 * date         : 2023/07/08
 * description  : BoardController
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/08       lia          최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final com.soomin.board.board.service.BoardService boardService;

    /**
     * 글 작성 페이지 출력
     *
     * @return 글 작성 페이지
     */
    @GetMapping("/save")
    public String saveForm() {
        return "boardSave";
    }

    /**
     * 글 작성
     *
     * @param   boardDto    게시글 정보
     * @return  작성 결과
     */
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDto boardDto) {
        System.out.println("boardDto = " + boardDto);
        boardService.save(boardDto);
        return "boardIndex";
    }

    /**
     * 게시글 리스트 조회
     *
     * @param   model   model
     * @return  조회 결과
     */
    @GetMapping("/")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<BoardDto> boardDtoList = boardService.findAll();
        model.addAttribute("boardList", boardDtoList);
        return "boardList";
    }
}
