package com.soomin.board.member.entity;

import com.soomin.board.member.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * fileName     : MemberEntity
 * author       : lia
 * date         : 2023/07/07
 * description  : MemberEntity
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/07       lia          최초 생성
 */
@Entity
@Setter
@Getter
//@Table(name = "member_table")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long    id;             // 회원 고유번호

    @Column(unique = true)
    private String  memberEmail;    // 회원 이메일

    @Column
    private String  memberPassword; // 회원 비밀번호

    @Column
    private String  memberName;     // 회원 이름

    /**
     * Dto -> Entity 변환
     *
     * @param   memberDTO       회원 정보
     * @return  member entity
     */
    public static MemberEntity toMemberEntity(MemberDto memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }
}
