package com.soomin.board.board.service;

import com.soomin.board.board.dto.BoardDto;
import com.soomin.board.board.entity.BoardEntity;
import com.soomin.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
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
}
