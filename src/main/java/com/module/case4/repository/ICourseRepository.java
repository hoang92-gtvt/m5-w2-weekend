package com.module.case4.repository;

import com.module.case4.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course,Long> {
    Course findCourseByName(String keyname);

}
