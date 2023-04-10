package com.jpa.practice.summary.repository;


import com.jpa.practice.summary.domain.Article;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    public Long save(Article article) {

        List<Article> isExist = em.createQuery(
                "select a from Article a where a.contents = :contents", Article.class)
                .setParameter("contents", article.getContents())
                .getResultList();

        if(isExist.isEmpty()){
        em.persist(article);
        return article.getId();
        }

        return isExist.get(0).getId();

    }

    public Article findOne(Long id){
        return em.find(Article.class, id);
    }
}
