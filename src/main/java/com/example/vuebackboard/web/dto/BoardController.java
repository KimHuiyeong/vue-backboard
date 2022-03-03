package com.example.vuebackboard.web.dto;

import com.example.vuebackboard.entity.BoardEntity;
import com.example.vuebackboard.model.Header;
import com.example.vuebackboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public Header<List<BoardDto>> boardList(@PageableDefault(sort = {"idx"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return boardService.getBoardList(pageable);
    }

    @GetMapping("/board/{id}")
    public BoardDto getBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

    @PostMapping("/board")
    public BoardEntity create(@RequestBody BoardDto dto){
        return boardService.create(dto);
    }

    @PatchMapping("/board")
    public BoardEntity update(@RequestBody BoardDto dto){
        return boardService.update(dto);
    }

    @DeleteMapping("/board/{id}")
    public void delete(@PathVariable Long id){
         boardService.delete(id);
    }
}
