package com.capgemini.talent.skill.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.talent.skill.api.entity.TalentPoolCandidate;
import com.capgemini.talent.skill.api.repository.TalenPoolCandidatesRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service
public class TalenPoolCandidatesServiceImpl implements TalenPoolCandidatesService {

	private final TalenPoolCandidatesRepository employeeSkillRepository;

	public TalenPoolCandidatesServiceImpl(TalenPoolCandidatesRepository employeeSkillRepository) {
		this.employeeSkillRepository = employeeSkillRepository;
	}

	public List<TalentPoolCandidate> getSkillsOfEmployee(Long id) {
		return employeeSkillRepository.findByid(id);
	}

	public TalentPoolCandidate create(TalentPoolCandidate employeeSkill) {
		return employeeSkillRepository.save(employeeSkill);
	}

	public TalentPoolCandidate update(Long id, TalentPoolCandidate employeeSkill) {
		employeeSkill.setId(id);
		return employeeSkillRepository.save(employeeSkill);
	}

	public TalentPoolCandidate patch(Long id, TalentPoolCandidate employeeSkill) {
		return employeeSkillRepository.findById(id).map(existingSkill -> {
			if (employeeSkill.getName() != null)
				existingSkill.setName(employeeSkill.getName());
			if (employeeSkill.getGrade() != null)
				existingSkill.setGrade(employeeSkill.getGrade());
			if (employeeSkill.getTechnologies() != null)
				existingSkill.setTechnologies(employeeSkill.getTechnologies());
			if (employeeSkill.getRole() != null)
				existingSkill.setRole(employeeSkill.getRole());
			if (employeeSkill.getCandidateCertification() != null)
				existingSkill.setCandidateCertification(employeeSkill.getCandidateCertification());
			if (employeeSkill.getLanguages() != null)
				existingSkill.setLanguages(employeeSkill.getLanguages());
			return employeeSkillRepository.save(existingSkill);
		}).orElseThrow(() -> new RuntimeException("EmployeeSkill not found"));
	}

	public void delete(Long id) {
		employeeSkillRepository.deleteById(id);
	}

	public void uploadExcel(MultipartFile file) {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
			List<TalentPoolCandidate> employeeSkills = new ArrayList<>();
			String[] values;

			while ((values = csvReader.readNext()) != null) {
				TalentPoolCandidate candidates = new TalentPoolCandidate();
				candidates.setName(values[0]);
				candidates.setGrade(values[1]);
				candidates.setTechnologies(values[2]);
				candidates.setRole(values[3]);
				candidates.setCandidateCertification(values[4]);
				candidates.setLanguages(values[5]);
				employeeSkills.add(candidates);
			}
			employeeSkillRepository.saveAll(employeeSkills);
		} catch (Exception e) {
			throw new RuntimeException("Failed to process CSV file", e);
		}
	}

}
