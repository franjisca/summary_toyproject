package com.jpa.practice.summary.service;

import com.jpa.practice.summary.domain.Article;
import com.jpa.practice.summary.repository.ArticleRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    EntityManager em;

    @Test
    public void save(){
        Article article = new Article("title", "contents", LocalDateTime.now());
        Long articleId = articleService.save(article);
        em.flush();
        assertEquals(article, articleRepository.findOne(articleId));
    }

}