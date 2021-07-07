package com.module.case4.controller;

import com.module.case4.model.course.Course;
import com.module.case4.service.ICourseService;
import com.module.case4.service.impl.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping("/findAllCourse")
    public ResponseEntity<List<Course>> getAllCourse(){
        List<Course> courses =  courseService.getAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}/find")
    public ResponseEntity<?> getCourse(@PathVariable Long id){
        return new ResponseEntity<>(courseService.getOne(id),HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Course course){
        courseService.save(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Course course){
        Optional<Course> course1 = courseService.getOne(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("/{keyName}")
    public ResponseEntity<?> findCourseByName(@PathVariable String keyName){
        return new ResponseEntity<>(courseService.findCourseByName(keyName), HttpStatus.OK);
    }
}
