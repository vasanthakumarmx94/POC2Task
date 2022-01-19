package com.neosoft.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.myexception.ResourceNotFoundException;
import com.neosoft.model.Project;
import com.neosoft.model.Student;
import com.neosoft.repository.ProjectRepository;
import com.neosoft.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	ProjectRepository projectRepository;

	// http://localhost:8080/students
	@GetMapping("/students")
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

//  http://localhost:8080/Project/3/students
	@PostMapping("/Project/{projId}/students")
	public Student createstudent(@PathVariable Long projId, @Valid @RequestBody Student student){
		return projectRepository.findById(projId).map(project -> {
			student.setProject(project);
			return studentRepository.save(student);
		}).orElseThrow(() -> new ResourceNotFoundException("Project", "projectid",projId ));
	}
	
	@PostMapping("/project")
	public Project createProject(@Valid @RequestBody Project project) {
		return projectRepository.save(project);
	}
	
	//  http://localhost:8080/posts/1
	@PutMapping("/project/{projId}")
	public Project updateProject(@PathVariable Long projId, @Valid @RequestBody Project projReq) {
	return projectRepository.findById(projId).map(project -> {
		project.setDuration(projReq.getDuration());
		project.setProjname(projReq.getProjname());
		
		return projectRepository.save(project);
	}).orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projId));
	}

	// http://localhost:8080/employee/1
	@GetMapping("/students/{id}")
	public Optional<Student> getStudentbyid(@PathVariable Long id) {
		return studentRepository.findById(id);
	}

	// http://localhost:8080/employee/1
	@DeleteMapping("/students/{id}")
	public void deleteStudentbyid(@PathVariable Long id) {
		studentRepository.deleteById(id);
	}

}
