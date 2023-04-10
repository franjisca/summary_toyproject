package com.jpa.practice.summary.controller;


import com.jpa.practice.summary.domain.Summary;
import com.jpa.practice.summary.dto.SummaryDto;
import com.jpa.practice.summary.service.ArticleService;
import com.jpa.practice.summary.service.SummaryService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/summary")
public class SummaryController {

    private final ArticleService articleService;
    private final SummaryService summaryService;

    @GetMapping("/write")
    public String writePage(@RequestParam("articleId") Long articleId,Model model){
        model.addAttribute("articleId", articleId);
        return "summary/write";
    }
    @PostMapping("/write")
    public String write(@Valid @ModelAttribute("summary") SummaryDto summaryDto){

        log.info("작동됨");
        log.info("summary = {}", summaryDto);

        return "summary/write";
    }
}
