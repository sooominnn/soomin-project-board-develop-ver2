package com.soomin.board.member.service;

import com.soomin.board.member.dto.MemberDto;
import com.soomin.board.member.entity.MemberEntity;
import com.soomin.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * fileName     : MemberService
 * author       : lia
 * date         : 2023/07/07
 * description  : MemberService
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/07       lia          최초 생성
 */
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     *
     * @param memberDto 회원 정보
     */
    public void save(MemberDto memberDto) {

        // 1. dto -> entity 변환
        // 2. repository save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        memberRepository.save(memberEntity);
        // repository save 메서드 호출 (entity 객체를 넘겨줘야 함 / dto-controller, entity-repository)

    }
}
