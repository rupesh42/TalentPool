package com.capgemini.talent.skill.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.talent.skill.api.entity.TalentPoolCandidate;
import com.capgemini.talent.skill.api.service.TalenPoolCandidatesService;

@RestController
@RequestMapping("/api/v1/skills")
public class TalenPoolCandidatesController {

	private final TalenPoolCandidatesService skillService;

	public TalenPoolCandidatesController(TalenPoolCandidatesService skillService) {
		this.skillService = skillService;
	}

	@GetMapping("/{id}/skills")
	public ResponseEntity<List<TalentPoolCandidate>> getSkillsOfEmployee(@PathVariable Long id) {
		var skills = skillService.getSkillsOfEmployee(id);
		return ResponseEntity.ok(skills);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TalentPoolCandidate create(@RequestBody TalentPoolCandidate employeeSkill) {
		return skillService.create(employeeSkill);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TalentPoolCandidate> update(@PathVariable Long id,
			@RequestBody TalentPoolCandidate employeeSkill) {
		return ResponseEntity.ok(skillService.update(id, employeeSkill));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<TalentPoolCandidate> patch(@PathVariable Long id,
			@RequestBody TalentPoolCandidate employeeSkill) {
		return ResponseEntity.ok(skillService.patch(id, employeeSkill));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		skillService.delete(id);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
		skillService.uploadExcel(file);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("File uploaded successfully");
	}
}
