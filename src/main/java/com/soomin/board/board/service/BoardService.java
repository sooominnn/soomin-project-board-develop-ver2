package com.soomin.board.board.service;

import com.soomin.board.board.dto.BoardDto;
import com.soomin.board.board.entity.BoardEntity;
import com.soomin.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * fileName     : BoardService
 * author       : lia
 * date         : 2023/07/08
 * description  : BoardService
 * ===========================================================
 * DATE            AUTHOR         NOTE
 * -----------------------------------------------------------
 * 2023/07/08       lia          최초 생성
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDto boardDto) {

        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto);
        boardRepository.save(boardEntity);
    }

    public List<BoardDto> findAll() {

        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDto> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDto.toBoardDto(boardEntity));
        }
        return boardDTOList;
    }

    /**
     * 게시글 조회수 업데이트
     *
     * @param id 게시글 고유번호
     */
    public void updateHits(Long id) {

        boardRepository.updateHits(id);
    }

    /**
     * 게시글 고유번호로 게시글 조회
     *
     * @param   id  게시글 고유번호
     * @return  조회 결과
     */
    public BoardDto findById(Long id) {

        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDto boardDto = BoardDto.toBoardDto(boardEntity);
            return boardDto;
        } else {
            return null;
        }
    }

    /**
     * 게시글 수정
     *
     * @param   boardDto    게시글 정보
     * @return  수정 결과
     */
    public BoardDto update(BoardDto boardDto) {

        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDto);
        boardRepository.save(boardEntity);
        return findById(boardDto.getId());
    }

    /**
     * 게시글 삭제
     *
     * @param id    게시글 고유번호
     */
    public void delete(Long id) {

        boardRepository.deleteById(id);
    }

    /**
     * 페이징 처리
     *
     * @param   pageable        pageable
     * @return  페이징 처리 결과
     */
    public Page<BoardDto> paging(Pageable pageable) {

        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<BoardDto> boardDTOS = boardEntities.map(board -> new BoardDto(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getBoardCreatedTime()));
        return boardDTOS;
    }
}
