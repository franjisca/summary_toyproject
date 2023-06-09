package com.jpa.practice.summary.domain;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    private String title;

    @Column(length = 5000)
    private String contents;
    private LocalDateTime date;


    // @OneToOne(mappedBy = "article", fetch = FetchType.LAZY)
    // private Summary summary;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "article")
    private Summary summary;

    public Article() {
    }

    public Article(String title, String contents, LocalDateTime date) {
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

}
