package com.jpa.practice.summary.domain;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Summary {

    @Id
    @GeneratedValue
    @Column(name = "summary_id")
    private Long id;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private Article article;
    private String title;
    private String contents;
    private LocalDateTime createDate;

    public Summary() {
    }

    public Summary(Article article, String title, String contents, LocalDateTime createDate) {
        this.article = article;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
    }

    public Summary(Article article, String title, String contents) {
        this.article = article;
        this.title = title;
        this.contents = contents;
    }

    public void update(Summary summary) {
        this.title = summary.getTitle();
        this.contents = summary.getContents();
    }
}
