package com.soomin.board.board.repository;

import com.soomin.board.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);
}
