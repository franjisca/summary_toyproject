package com.jpa.practice.summary.service;

import com.jpa.practice.summary.domain.Summary;
import com.jpa.practice.summary.repository.SummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SummaryService {

    private final SummaryRepository summaryRepository;


    @Transactional
    public void save(Summary summary){
        summaryRepository.save(summary);
    }

    public Summary unitSummary(Long id){
        return summaryRepository.unitSummary(id);
    }

    public List<Summary> all(){
        return summaryRepository.all();
    }

    @Transactional
    public void updateSummary(Long id,Summary updateSummary){
       summaryRepository.updateSummary(id ,updateSummary);
    }

    @Transactional
    public void delete(Long id){
        summaryRepository.delete(id);
    }
}
