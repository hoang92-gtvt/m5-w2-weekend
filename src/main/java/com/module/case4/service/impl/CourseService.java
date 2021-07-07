package com.module.case4.service.impl;

import com.module.case4.model.course.Course;
import com.module.case4.repository.ICourseRepository;
import com.module.case4.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private ICourseRepository repoCourse;

    @Override
    public List getAll() {
        return repoCourse.findAll();
    }

    @Override
    public Optional getOne(Long id) {
        return repoCourse.findById(id);
    }

    @Override
    public void save(Course course) {
        repoCourse.save(course);
    }


    @Override
    public void delete(Long id) {
    repoCourse.deleteById(id);
    }

    @Override
    public Course findCourseByName(String keyname) {
        return repoCourse.findCourseByName(keyname);
    }
}
