package com.soomin.board.member.entity;

import com.soomin.board.board.entity.BoardEntity;
import com.soomin.board.comment.entity.CommentEntity;
import com.soomin.board.member.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "member_table")
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

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();


    /**
     * Dto -> Entity 변환
     *
     * @param   memberDto       회원 정보
     * @return  member entity
     */
    public static MemberEntity toMemberEntity(MemberDto memberDto) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberName(memberDto.getMemberName());
        return memberEntity;
    }

    /**
     * 회원 정보 수정
     *
     * @param   memberDto   회원 정보
     * @return  수정 결과
     */
    public static MemberEntity toUpdateMemberEntity(MemberDto memberDto) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDto.getId());
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberName(memberDto.getMemberName());
        return memberEntity;
    }
}
