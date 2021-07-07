package com.module.case4.controller;

import com.module.case4.model.course.Course;
import com.module.case4.model.course.District;
import com.module.case4.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DistrictController {
   @Autowired
   private IDistrictService districtService;
    @GetMapping("")
    public ResponseEntity<List<District>> getAllDistrict(){
        List<District> districts=  districtService.getAll();
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping("/{id}/find")
    public ResponseEntity<?> getCourse(@PathVariable Long id){
        return new ResponseEntity<>(districtService.getOne(id),HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody District district){
        districtService.save(district);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody District district){
        Optional<District> district1= districtService.getOne(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
       districtService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
