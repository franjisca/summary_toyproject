package com.jpa.practice.summary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class SummaryDto {

    private Long articleId;

    private Long summaryId;
    private String title;
    private String contents;
}
