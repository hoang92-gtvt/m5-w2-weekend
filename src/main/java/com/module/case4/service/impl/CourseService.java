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
    public Course findCourseByName(String keyname) {
        return repoCourse.findCourseByKeyName(keyname);
    }

    @Override
    public Iterable<Course> getAll() {
        return repoCourse.findAll();
    }

    @Override
    public Optional<Course> getByID(Long id) {
        return repoCourse.findById(id);
    }

    @Override
    public Course save(Course course) {
      return  repoCourse.save(course);
    }

    @Override
    public void delete(Long id) {
        repoCourse.deleteById(id);
    }
}
