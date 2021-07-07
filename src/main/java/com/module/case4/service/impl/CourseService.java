package com.module.case4.service.impl;

import com.module.case4.model.course.Course;
import com.module.case4.repository.ICourseRepository;
import com.module.case4.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private ICourseRepository repoCourse;


    @Override
    public Iterable<Course> fillAll() {
        return repoCourse.findAll();
    }

    @Override
    public void updateOrSave(Course course) {
        repoCourse.save(course);
    }

    @Override
    public void delete(Long id) {
        repoCourse.deleteById(id);
    }


    @Override
    public Optional<Course> findById(Long id) {
        return repoCourse.findById(id);
    }

    @Override
    public Course getCusByName(String keyname) {
        return repoCourse.findCourseByName(keyname);
    }
}
