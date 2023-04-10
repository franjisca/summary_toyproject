package com.jpa.practice.summary.controller;


import com.jpa.practice.summary.crawling.ArticleCrawler;
import com.jpa.practice.summary.domain.Article;
import com.jpa.practice.summary.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String main(Model model){

        Article article = ArticleCrawler.workCrawler("https://www.mk.co.kr/news/world/10709057");

        Long saveId = articleService.save(article);

        Article viewArticle = articleService.findOne(saveId);
        model.addAttribute("article", viewArticle);

        return "summary/main";
    }
}
