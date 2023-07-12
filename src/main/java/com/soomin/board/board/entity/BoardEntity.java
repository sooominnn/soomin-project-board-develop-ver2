package com.soomin.board.board.entity;

import com.soomin.board.board.dto.BoardDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * fileName     : BoardEntity
 * author       : lia
 * date         : 2023/07/08
 * description  : BoardEntity
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/08       lia          최초 생성
 */
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column // 크기 255, null 가능
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDto boardDto) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setBoardPass(boardDto.getBoardPass());
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContents(boardDto.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}
