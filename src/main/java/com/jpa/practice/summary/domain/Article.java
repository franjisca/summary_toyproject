package com.jpa.practice.summary.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    private String title;
    private String contents;
    private LocalDateTime date;

    public Article() {
    }

    public Article(String title, String contents, LocalDateTime date) {
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

}
