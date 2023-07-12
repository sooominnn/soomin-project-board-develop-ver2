package com.soomin.board.member.dto;

import com.soomin.board.member.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * fileName     : MemberDto
 * author       : lia
 * date         : 2023/07/07
 * description  : MemberDto
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/07       lia          최초 생성
 */
@Getter
@Setter
public class MemberDto {

    private Long    id;                 // 회원 고유번호
    private String  memberEmail;        // 회원 이메일
    private String  memberPassword;     // 회원 비밀번호
    private String  memberName;         // 회원 이름

    /**
     * entity -> dto 변환
     *
     * @param   memberEntity    회원 entity
     * @return  회원 entity
     */
    public static MemberDto toMemberDto(MemberEntity memberEntity) {
        MemberDto memberDTO = new MemberDto();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        return memberDTO;
    }
}
