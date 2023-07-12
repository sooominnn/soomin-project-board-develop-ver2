package com.soomin.board.comment.repository;

import com.soomin.board.board.entity.BoardEntity;
import com.soomin.board.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * fileName     : CommentRepository
 * author       : lia
 * date         : 2023/07/08
 * description  : CommentRepository
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/08       lia          최초 생성
 */
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    // select * from comment_table where board_id=? order by id desc;
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
