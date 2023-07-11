package com.soomin.board.member.dto;

import lombok.*;

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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {

    private Long    id;                 // 회원 고유번호
    private String  memberEmail;        // 회원 이메일
    private String  memberPassword;     // 회원 비밀번호
    private String  memberName;         // 회원 이름
}
