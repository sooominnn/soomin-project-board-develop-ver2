package com.soomin.board.comment.dto;

import com.soomin.board.comment.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * fileName     : CommentDto
 * author       : lia
 * date         : 2023/07/08
 * description  : CommentDto
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/08       lia          최초 생성
 */
@Getter
@Setter
@ToString
public class CommentDto {

    private Long            id;
    private String          commentWriter;
    private String          commentContents;
    private Long            boardId;
//    private LocalDateTime   commentCreatedTime;

    public static CommentDto toCommentDto(CommentEntity commentEntity, Long boardId) {
        CommentDto commentDTO = new CommentDto();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setBoardId(boardId);
        return commentDTO;
    }
}
