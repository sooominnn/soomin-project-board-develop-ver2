package com.soomin.board.member.service;

import com.soomin.board.member.dto.MemberDto;
import com.soomin.board.member.entity.MemberEntity;
import com.soomin.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * 로그인
     *
     * @param memberDto 회원 정보
     * @return 로그인 결과
     */
    public MemberDto login(MemberDto memberDto) {
        // 1. 회원이 입력한 이메일로 DB 에서 조회
        // 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDto.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDto.getMemberPassword())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                MemberDto dto = MemberDto.toMemberDto(memberEntity);
                return dto;
            } else {
                // 비밀번호 불일치(로그인실패)
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
            return null;
        }
    }

    /**
     * 회원 리스트
     *
     * @return 회원 리스트
     */
    public List<MemberDto> findAll() {

        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDtoList.add(MemberDto.toMemberDto(memberEntity));
//            MemberDto memberDto = MemberDto.toMemberDto(memberEntity);
//            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    /**
     * 회원 조회
     *
     * @param id 회원 고유번호
     * @return 회원 조회 결과
     */
    public MemberDto findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    /**
     * 이메일로 회원 정보 조회
     *
     * @param   myEmail 이메일
     * @return  조회 결과
     */
    public MemberDto updateForm(String myEmail) {

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    /**
     * 회원 정보 수정
     *
     * @param memberDto 회원 정보
     */
    public void update(MemberDto memberDto) {

        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDto));
    }
}
