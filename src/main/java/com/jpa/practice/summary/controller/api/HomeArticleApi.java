package com.jpa.practice.summary.controller.api;


import com.jpa.practice.summary.crawling.ArticleCrawler;
import com.jpa.practice.summary.domain.Article;
import com.jpa.practice.summary.repository.ArticleRepository;
import com.jpa.practice.summary.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeArticleApi {


    private final ArticleService articleService;

    @GetMapping("/article")
    public Article bringArticle(){

        Article article = ArticleCrawler.workCrawler("https://www.mk.co.kr/news/it/10709311");

        Long saveId = articleService.save(article);

        Article viewArticle = articleService.findOne(saveId);

        return viewArticle;
    }

}
