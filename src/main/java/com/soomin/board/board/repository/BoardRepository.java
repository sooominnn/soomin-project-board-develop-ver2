package com.soomin.board.board.repository;

import com.soomin.board.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * fileName     : BoardRepository
 * author       : lia
 * date         : 2023/07/08
 * description  : BoardRepository
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/08       lia          최초 생성
 */
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
