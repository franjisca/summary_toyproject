package com.jpa.practice.summary.controller.api;


import com.jpa.practice.summary.domain.Summary;
import com.jpa.practice.summary.dto.SummaryDto;
import com.jpa.practice.summary.service.ArticleService;
import com.jpa.practice.summary.service.SummaryService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SummaryApi {

    private final ArticleService articleService;
    private final SummaryService summaryService;


    @PostMapping("/write")
    public SummaryResponse saveSummary(@RequestBody @Valid SummaryDto summaryDto){
        Summary summary = new Summary(articleService.findOne(summaryDto.getArticleId()), summaryDto.getTitle(), summaryDto.getContents(), LocalDateTime.now());
        Long saveId = summaryService.save(summary);
        return new SummaryResponse(summaryService.unitSummary(saveId));
    }

    @PostMapping("/update")
    public SummaryResponse updateSummary(@RequestBody @Valid SummaryDto updateDto){
        Summary summary = new Summary(updateDto.getSummaryId(), updateDto.getTitle(), updateDto.getContents());
        summaryService.updateSummary(updateDto.getSummaryId(), summary);
        return new SummaryResponse(summaryService.unitSummary(updateDto.getSummaryId()));
    }

    @GetMapping("/delete")
    public MessageJson deleteSummary(@RequestParam("summaryId") Long summaryId){
        summaryService.delete(summaryId);
        try {
        Summary summary = summaryService.unitSummary(summaryId);

            if(summary != null){
                return new MessageJson("다시 시도해주세요. 삭제되지 않았습니다.");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new MessageJson("성공적으로 삭제되었습니다.");
    }

    @GetMapping("/view")
    public SummaryResponse viewSummary(@RequestParam("summaryId") Long summaryId){
        Summary summary = summaryService.unitSummary(summaryId);
        return new SummaryResponse(summary);
    }

    @GetMapping("/list")
    public List<SummaryResponse> summaryList(){
        return summaryService.all().stream().map(summary -> new SummaryResponse(summary)).toList();
    }

    @Data
    @Getter
    private class SummaryResponse {
        private Long summaryId;
        private Long articleId;

        private String title;
        private String contents;

        private LocalDateTime createDate;

        public SummaryResponse(Summary summary) {
            this.summaryId = summary.getId();

            articleService.findOne(summary.getArticle().getId());
            this.articleId = summary.getArticle().getId();
            this.title = summary.getTitle();
            this.contents = summary.getContents();
            this.createDate = summary.getCreateDate();
        }
    }

    @Data
    private class MessageJson {
        private String message;

        public MessageJson(String message) {
            this.message = message;
        }
    }


}
