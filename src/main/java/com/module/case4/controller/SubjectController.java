package com.module.case4.controller;

import com.module.case4.model.course.District;
import com.module.case4.model.course.Subject;
import com.module.case4.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;
    @GetMapping("")
    public ResponseEntity<Iterable<Subject>> getAllCourse() {
        Iterable<Subject> districts = subjectService.getAll();
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping("/{id}/find")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        return new ResponseEntity<>(subjectService.getByID(id), HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Subject subject) {
        subjectService.save(subject);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Subject subject) {
        Optional<Subject> district1 = subjectService.getByID(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
      subjectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
