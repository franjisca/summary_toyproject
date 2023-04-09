package com.jpa.practice.summary.repository;


import com.jpa.practice.summary.domain.Summary;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SummaryRepository {


    private final EntityManager em;

    public void save(Summary summary){
        em.persist(summary);
    }

    public Summary unitSummary(Long id){
        return em.find(Summary.class, id);
    }

    public List<Summary> all(){
        return em.createQuery("select s from Summary s", Summary.class).getResultList();
    }

    public void updateSummary(Summary updateSummary){
        Summary findSummary = em.find(Summary.class, updateSummary.getId());
        findSummary.update(updateSummary);
    }

    public void delete(Long id){
        Summary summary = em.find(Summary.class, id);
        em.remove(summary);
    }

}
