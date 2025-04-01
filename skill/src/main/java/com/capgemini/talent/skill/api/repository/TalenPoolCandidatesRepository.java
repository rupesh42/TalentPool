package com.capgemini.talent.skill.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.talent.skill.api.entity.TalentPoolCandidate;

@Repository
public interface TalenPoolCandidatesRepository extends JpaRepository<TalentPoolCandidate, Long> {

	List<TalentPoolCandidate> findByid(Long id);

}
