package com.soomin.board.member.repository;

import com.soomin.board.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * fileName     : MemberRepository
 * author       : lia
 * date         : 2023/07/07
 * description  : MemberRepository
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/07       lia          최초 생성
 */
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
