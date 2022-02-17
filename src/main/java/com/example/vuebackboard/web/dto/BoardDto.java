package com.example.vuebackboard.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private Long idx;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime createdAt;

}
