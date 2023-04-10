package com.jpa.practice.summary.dto;

import com.jpa.practice.summary.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@AllArgsConstructor
@Data
public class SummaryDto {

    private String articleId;
    private String title;
    private String contents;
}
