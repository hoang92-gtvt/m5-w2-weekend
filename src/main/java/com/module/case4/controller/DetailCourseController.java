package com.module.case4.controller;

import com.module.case4.model.course.DetailCourse;
import com.module.case4.service.IDetailCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/detail")
public class DetailCourseController {
    @Autowired
    IDetailCourseService detailCourseService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody DetailCourse detailCourse){
        detailCourseService.save(detailCourse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<?> showAll(){
        return new ResponseEntity<>(detailCourseService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable Long id){
        Optional<DetailCourse> detailCourse = detailCourseService.getByID(id);
        if (!detailCourse.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(detailCourse.get(),HttpStatus.OK);
    }

//    @GetMapping

}
