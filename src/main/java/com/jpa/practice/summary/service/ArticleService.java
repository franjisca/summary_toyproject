package com.jpa.practice.summary.service;


import com.jpa.practice.summary.domain.Article;
import com.jpa.practice.summary.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Long save(Article article) {
        articleRepository.save(article);
        return article.getId();
    }

    public Article findOne(Long id){
        return articleRepository.findOne(id);
    }
}
