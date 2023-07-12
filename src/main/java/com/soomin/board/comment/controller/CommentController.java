package com.soomin.board.comment.controller;

import com.soomin.board.comment.dto.CommentDto;
import com.soomin.board.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * fileName     : CommentController
 * author       : lia
 * date         : 2023/07/08
 * description  : CommentController
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/08       lia          최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 생성
     *
     * @param   commentDto  댓글 정보
     * @return  댓글 생성 결과
     */
    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CommentDto commentDto) {
        System.out.println("commentDTO = " + commentDto);
        Long saveResult = commentService.save(commentDto);
        if (saveResult != null) {
            List<CommentDto> commentDTOList = commentService.findAll(commentDto.getBoardId());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }
}
