package com.soomin.board.board.controller;

import com.soomin.board.board.dto.BoardDto;
import com.soomin.board.board.service.BoardService;
import com.soomin.board.comment.dto.CommentDto;
import com.soomin.board.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    private final BoardService boardService;
    private final CommentService commentService;

    /**
     * 게시글 작성 페이지 출력
     *
     * @return 글 작성 페이지
     */
    @GetMapping("/save")
    public String saveForm() {
        return "boardSave";
    }

    /**
     * 게시글 작성
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

    /**
     * 게시글 상세 조회
     *
     * @param   id          게시글 고유번호
     * @param   model       model
     * @param   pageable    pageable
     * @return  조회 결과
     */
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model, @PageableDefault(page=1) Pageable pageable) {

        //해당 게시글 조회수 하나 올리고 -> 게시글 데이터 가져와서 detail.html에 출력
        boardService.updateHits(id);
        BoardDto boardDto = boardService.findById(id);
        // 댓글 목록 가져오기
        List<CommentDto> commentDtoList = commentService.findAll(id);
        model.addAttribute("commentList", commentDtoList);
        model.addAttribute("board", boardDto);
        model.addAttribute("page", pageable.getPageNumber());
        return "boardDetail";
    }

    /**
     * 게시글 수정 페이지 출력
     *
     * @param   id                  회원 고유번호
     * @param   model               model
     * @return  게시글 수정 페이지 출력
     */
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDto);
        return "boardUpdate";
    }

    /**
     * 게시글 수정
     *
     * @param   boardDto    게시글 정보
     * @param   model       model
     * @return  수정 결과
     */
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDto boardDto, Model model) {
        BoardDto board = boardService.update(boardDto);
        model.addAttribute("board", board);
        return "boardDetail";
    }

    /**
     * 게시글 삭제
     *
     * @param   id      게시글 고유번호
     * @return  삭제 결과
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }

    /**
     * 페이징 처리
     *
     * @param   pageable        pageable
     * @param   model           model
     * @return  페이징 처리 결과
     */
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDto> boardList = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";
    }
}
