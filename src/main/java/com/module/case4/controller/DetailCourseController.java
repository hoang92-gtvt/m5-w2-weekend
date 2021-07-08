package com.module.case4.controller;

import com.module.case4.model.course.DetailCourse;
import com.module.case4.service.IDetailCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detail")
@CrossOrigin(origins = "*")
public class DetailCourseController {
    @Autowired
    IDetailCourseService detailCourseService;

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody DetailCourse detailCourse){
        return new ResponseEntity<>(detailCourseService.save(detailCourse),HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<?> showAll(){
        List<DetailCourse> detailCourse = (List<DetailCourse>) detailCourseService.getAll();
        if (detailCourse.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(detailCourse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable Long id){
        Optional<DetailCourse> detailCourse = detailCourseService.getByID(id);
        if (!detailCourse.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(detailCourse.get(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<DetailCourse> detailCourse = detailCourseService.getByID(id);
        if (!detailCourse.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DetailCourse detailCourse){
        Optional<DetailCourse> detailCourse1 = detailCourseService.getByID(id);
        if (!detailCourse1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
           detailCourse.setId(detailCourse1.get().getId());
           detailCourseService.save(detailCourse);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/find/{name}/{status}")
    public ResponseEntity<?> findByDetailByStatus(@PathVariable String name,@PathVariable String status){
        List<DetailCourse> detailCourse = (List<DetailCourse>) detailCourseService.findDetailByUserStudentAndStatus(name,status);
        if (detailCourse!=null){
            return new ResponseEntity<>(detailCourse,HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
