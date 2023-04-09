package com.jpa.practice.summary.repository;


import com.jpa.practice.summary.domain.Article;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    public void save(Article article) {
        em.persist(article);
    }

    public Article findOne(Long id){
        return em.find(Article.class, id);
    }
}
