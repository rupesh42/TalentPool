package com.capgemini.talent.skill.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.capgemini.talent.skill.api.entity.TalentPoolCandidate;

public interface TalenPoolCandidatesService {

	public List<TalentPoolCandidate> getSkillsOfEmployee(Long id);

	public TalentPoolCandidate create(TalentPoolCandidate employeeSkill);

	public TalentPoolCandidate update(Long id, TalentPoolCandidate employeeSkill);

	public TalentPoolCandidate patch(Long id, TalentPoolCandidate employeeSkill);

	public void delete(Long id);

	public void uploadExcel(MultipartFile file);

}
