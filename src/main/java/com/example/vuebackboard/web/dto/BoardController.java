package com.example.vuebackboard.web.dto;

import com.example.vuebackboard.entity.BoardEntity;
import com.example.vuebackboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public List<BoardDto> boardList(){
        return boardService.getBoardList();
    }

    @GetMapping("/board/{id}")
    public BoardDto getBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

    @PostMapping("/board")
    public BoardEntity create(BoardDto dto){
        return boardService.create(dto);
    }

    @PatchMapping("/board/update")
    public BoardEntity update(BoardDto dto){
        return boardService.update(dto);
    }

    @DeleteMapping("/board/delete/{id}")
    public void delete(@PathVariable Long id){
         boardService.delete(id);
    }
}
