package com.jpa.practice.summary.crawling;

import com.jpa.practice.summary.domain.Article;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class ArticleCrawler{
        private static Article article;
        private static String title = "";
        private static String contents = "";
        private static LocalDateTime date;

    public static Article workCrawler(String url) throws DateTimeParseException {

        Document doc;

        // div class ="time_area" : 작성일
        // h2 class ="news_ttl" : 제목
        // div class = "news_cnt_detail_wrap": 내용
        try {
            doc = Jsoup.connect(url).get();

            date = LocalDateTime.parse(
                    doc.
                            selectFirst(".registration").
                            text().
                            replace("입력 : ", ""),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            );

            // 기사 title
            title = doc.getElementsByTag("title").text();

            // 기사내용
            contents = doc.getElementsByClass("news_cnt_detail_wrap").text();

        }catch (Exception e){
            e.printStackTrace();
        }

        return new Article(title, contents, date);
    }

}
