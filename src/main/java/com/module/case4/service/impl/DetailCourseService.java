package com.module.case4.service.impl;

import com.module.case4.model.course.DetailCourse;
import com.module.case4.repository.IDetailCourseRepository;
import com.module.case4.service.IDetailCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    @Override
    public Iterable<DetailCourse> findAllByStatusContaining(String status) {
        return repository.findDetailCourseByStatus(status);
    }

    @Override
    public List<DetailCourse> findDetailByUserStudentAndStatus(String name, String status) {
        List<DetailCourse> detailCourses = new ArrayList<>();
        List<DetailCourse> detailCourses1 = repository.findAll();
        for (int i = 0; i < detailCourses1.size(); i++){
            if (detailCourses1.get(i).getUserStudent().getName().equals(name)&&
                    detailCourses1.get(i).getStatus().equals(status)){
                detailCourses.add(detailCourses1.get(i));

            }
        }
        return detailCourses;
    }

}
