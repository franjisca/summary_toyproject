package com.jpa.practice.summary.repository;


import com.jpa.practice.summary.domain.Summary;
import com.jpa.practice.summary.dto.SummaryDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SummaryRepository {


    private final EntityManager em;

    public Long save(Summary summary){

        List<Summary> summaries = em.createQuery("select s from Summary s where s.article=:article")
                                    .setParameter("article", summary.getArticle())
                                            .getResultList();

        if(summaries.isEmpty()){
        em.persist(summary);
        return summary.getId();
        }

        return 0L;
    }

    public Summary unitSummary(Long id){
        return em.find(Summary.class, id);
    }

    public List<Summary> all(){
        return em.createQuery("select s from Summary s", Summary.class).getResultList();
    }

    public void updateSummary(Long id, Summary updateSummary){
        Summary findSummary = em.find(Summary.class, id);
        findSummary.update(updateSummary);
    }

    public void delete(Long id) throws IllegalArgumentException{

        try {

        Summary summary = em.find(Summary.class, id);
        em.remove(summary);

        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
