package com.example.vuebackboard.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @DisplayName("1. 게시글 보기")
    @Test
    void 조회_테스트(){
        List<BoardEntity> list =  boardRepository.findAll();
        System.out.println(list);

        Optional<BoardEntity> board = boardRepository.findById(1L);
        System.out.println(board);
    }


    @DisplayName("2. 게시글 등록")
    @Test
    void 등록_테스트(){

        BoardEntity board = BoardEntity.builder()
                .createdAt(LocalDateTime.now())
                .author("HY1004")
                .contents("등록 테스트 야야")
                .title("등록 테스트")
                .build();
        boardRepository.save(board);

        System.out.println(boardRepository.findById(11L));
    }


    @DisplayName("3. 게시글 수정")
    @Test
    void 수정_테스트(){

        BoardEntity board = boardRepository.findById(1L).get();
        board.setTitle("1번 게시물 수정");
        board.setContents("1번 게시물은 수정되었습니다.");

        boardRepository.save(board);

        System.out.println(boardRepository.findById(1L));
    }


    @DisplayName("4. 게시글 삭제")
    @Test
    void 삭제_테스트(){

        BoardEntity board = BoardEntity.builder()
                .createdAt(LocalDateTime.now())
                .author("HY1004")
                .contents("등록 테스트 야야")
                .title("등록 테스트")
                .build();
        boardRepository.save(board);

        System.out.println(boardRepository.findById(11L));

        BoardEntity deleteBoard = boardRepository.findById(11L).get();
        boardRepository.delete(deleteBoard);

        System.out.println(boardRepository.findById(11L));
    }

}