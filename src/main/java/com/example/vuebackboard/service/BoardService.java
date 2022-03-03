package com.example.vuebackboard.service;

import com.example.vuebackboard.entity.BoardEntity;
import com.example.vuebackboard.entity.BoardRepository;
import com.example.vuebackboard.model.Header;
import com.example.vuebackboard.model.Pagination;
import com.example.vuebackboard.web.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public
    Header<List<BoardDto>> getBoardList(Pageable pageable) {
        ArrayList<BoardDto> dtos = new ArrayList<>();
        Page<BoardEntity> boardEntities = boardRepository.findAllByOrderByIdxDesc(pageable);
        boardEntities.forEach(entity -> {
            dtos.add(BoardDto.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .author(entity.getAuthor())
                    .createdAt(entity.getCreatedAt())
                    .build());
        });

        Pagination pagination = new Pagination(
                (int) boardEntities.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );

        return Header.OK(dtos, pagination);
    }

    public BoardDto getBoard(Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return BoardDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public BoardEntity create(BoardDto boardDto){
        BoardEntity board = BoardEntity.builder()
                .createdAt(LocalDateTime.now())
                .author(boardDto.getAuthor())
                .contents(boardDto.getContents())
                .title(boardDto.getTitle())
                .build();

        System.out.println("저장" + boardDto.toString());
        return boardRepository.save(board);
    }

    public BoardEntity update(BoardDto boardDto){
        System.out.println("수정" + boardDto.toString());
        BoardEntity board = boardRepository.findById(1L).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        return boardRepository.save(board);
    }

    public void delete(Long id){
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(entity);
    }

}
