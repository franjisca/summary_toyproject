package com.jpa.practice.summary.controller;


import ch.qos.logback.core.encoder.EchoEncoder;
import com.jpa.practice.summary.domain.Summary;
import com.jpa.practice.summary.dto.MessageDto;
import com.jpa.practice.summary.dto.SummaryDto;
import com.jpa.practice.summary.service.ArticleService;
import com.jpa.practice.summary.service.SummaryService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/summary")
public class SummaryController {

    private final ArticleService articleService;
    private final SummaryService summaryService;
    private MessageDto messageDto;

    @GetMapping("/write")
    public String writePage(@RequestParam("articleId") Long articleId,Model model){
        model.addAttribute("articleId", articleId);
        return "summary/write";
    }
    @PostMapping("/write")
    public String write(@ModelAttribute("summary") SummaryDto summaryDto, Model model){


        Summary summary = new Summary(articleService.findOne(summaryDto.getArticleId()), summaryDto.getTitle(), summaryDto.getContents(), LocalDateTime.now());

        Long saveId = summaryService.save(summary);

        log.info("saveId = {}", saveId);

        if(saveId == 0L){
        messageDto= new MessageDto("이미 요약된 기사입니다, 메인화면으로 이동합니다.", "/", true);
        model.addAttribute("message", messageDto);
        } else {
        messageDto= new MessageDto("성공적으로 작성되었습니다, 작성한 게시글로 이동합니다.", "/summary/view?summaryId="+saveId, false);
        model.addAttribute("message", messageDto);
        }

        return "common/alert";
    }

    @GetMapping("/view")
    public String view(@RequestParam("summaryId") Long summaryId, Model model){

        Summary summary = summaryService.unitSummary(summaryId);
        model.addAttribute("summary", summary);

        return "summary/view";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Summary> summaryList = summaryService.all();
        model.addAttribute("list", summaryList);
        return "summary/list";
    }

    @GetMapping("/update")
    public String updatePage(@RequestParam("summaryId") Long summaryId, Model model){
        Summary summary = summaryService.unitSummary(summaryId);
        model.addAttribute(summary);
        return "summary/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute SummaryDto updateDto, Model model){
        Summary summary = new Summary(updateDto.getSummaryId(),updateDto.getTitle(), updateDto.getContents());
        summaryService.updateSummary(updateDto.getSummaryId(), summary);

        messageDto = new MessageDto("수정되었습니다. 확인을 누르면 작성한 게시글로 이동합니다.",
                                    "/summary/view?summaryId="+updateDto.getSummaryId(),
                                    false);

        model.addAttribute("message", messageDto);

        return "common/alert";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("summaryId") Long summaryId, Model model){
        summaryService.delete(summaryId);
        try {
        Summary summary = summaryService.unitSummary(summaryId);

        if(summary != null){

            messageDto = new MessageDto("삭제되지 않았습니다. 다시 시도해주세요",
                    "redirect:",
                    false);

            model.addAttribute("message", messageDto);
        }

            messageDto = new MessageDto("삭제 되었습니다.",
                    "/",
                    false);

            model.addAttribute("message", messageDto);
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/";
        }


        return "common/alert";
    }
}
