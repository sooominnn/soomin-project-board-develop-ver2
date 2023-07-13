package com.soomin.board.board.dto;

import com.soomin.board.board.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * fileName     : BoardDto
 * author       : lia
 * date         : 2023/07/08
 * description  : BoardDto
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/08       lia          최초 생성
 */
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDto {

    private Long            id;                 // 게시글 고유번호
    private String          boardWriter;        // 게시글 작성자
    private String          boardPass;          // 게시글 비밀번호
    private String          boardTitle;         // 게시글 제목
    private String          boardContents;      // 게시글 내용
    private int             boardHits;          // 게시글 조회수
    private LocalDateTime   boardCreatedTime;   // 게시글 등록일시
    private LocalDateTime   boardUpdatedTime;   // 게시글 수정일시

    public static BoardDto toBoardDto(BoardEntity boardEntity) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(boardEntity.getId());
        boardDto.setBoardWriter(boardEntity.getBoardWriter());
        boardDto.setBoardPass(boardEntity.getBoardPass());
        boardDto.setBoardTitle(boardEntity.getBoardTitle());
        boardDto.setBoardContents(boardEntity.getBoardContents());
        boardDto.setBoardHits(boardEntity.getBoardHits());
        boardDto.setBoardCreatedTime(boardEntity.getBoardCreatedTime());
        boardDto.setBoardUpdatedTime(boardEntity.getBoardUpdatedTime());
        return boardDto;
    }

    // 생성자 추가
    public BoardDto(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }
}
