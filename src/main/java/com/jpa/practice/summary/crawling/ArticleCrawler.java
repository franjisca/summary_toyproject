package com.jpa.practice.summary.crawling;

import com.jpa.practice.summary.domain.Article;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ArticleCrawler{
    private String url;
    public Article workCrawler(String url) {
        Article article = null;

        Document doc;

        try {
            doc = Jsoup.connect(url).get();
            Elements data = doc.select("css class명이 올 곳");

            for(Element one: data) {
                // Article = data.xxx
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return article;
    }
}
