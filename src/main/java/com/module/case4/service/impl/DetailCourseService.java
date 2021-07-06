package com.module.case4.service.impl;

import com.module.case4.model.course.DetailCourse;
import com.module.case4.repository.IDetailCourseRepository;
import com.module.case4.service.IDetailCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailCourseService implements IDetailCourseService {

    @Autowired
    IDetailCourseRepository repository;

    @Override
    public Iterable<DetailCourse> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<DetailCourse> getByID(Long id) {
        return repository.findById(id);
    }

    @Override
    public DetailCourse save(DetailCourse detailCourse) {
        return repository.save(detailCourse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }
}
