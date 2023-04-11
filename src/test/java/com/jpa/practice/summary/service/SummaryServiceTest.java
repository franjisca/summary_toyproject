package com.jpa.practice.summary.service;

import com.jpa.practice.summary.domain.Article;
import com.jpa.practice.summary.domain.Summary;
import com.jpa.practice.summary.dto.SummaryDto;
import com.jpa.practice.summary.repository.SummaryRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class SummaryServiceTest {

    @Autowired
    SummaryService summaryService;

    @Autowired
    EntityManager em;

    @Test
    void updateSummary() {

        Article article = new Article("title", "contents", LocalDateTime.now());
        em.persist(article);
        Long id = article.getId();
        Summary summary = new Summary(article, "title", "summary contents", LocalDateTime.now());

        // Summary
        summaryService.save(summary);

        Summary updateSummary = new Summary(article, "title change", "summary contents change", LocalDateTime.now());

        summaryService.updateSummary(summary.getId(), updateSummary);

        Summary findsummary = summaryService.unitSummary(summary.getId());

        assertEquals(updateSummary.getTitle(), findsummary.getTitle());
        assertEquals(updateSummary.getContents(), findsummary.getContents());



    }

}