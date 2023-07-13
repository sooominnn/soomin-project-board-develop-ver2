package com.soomin.board.comment.service;

import com.soomin.board.board.entity.BoardEntity;
import com.soomin.board.board.repository.BoardRepository;
import com.soomin.board.comment.dto.CommentDto;
import com.soomin.board.comment.entity.CommentEntity;
import com.soomin.board.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * fileName     : CommentService
 * author       : lia
 * date         : 2023/07/08
 * description  : CommentService
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/08       lia          최초 생성
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    /**
     * 댓글 생성
     *
     * @param   commentDto  댓글 정보
     * @return  생성 결과
     */
    public Long save(CommentDto commentDto) {
        // BoardEntity 조회
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDto.getBoardId());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDto, boardEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }

    /**
     * 게시글에서 댓글 목록 조회
     *
     * @param   boardId 게시글 고유번호
     * @return  조회 결과
     */
    public List<CommentDto> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        // EntityList -> DTOList
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDto commentDto = CommentDto.toCommentDto(commentEntity, boardId);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }
}
