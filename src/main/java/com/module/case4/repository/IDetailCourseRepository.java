package com.module.case4.repository;

import com.module.case4.model.course.DetailCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetailCourseRepository extends JpaRepository<DetailCourse,Long> {
    Iterable<DetailCourse>findDetailCourseByStatus(String status);
//    Iterable<DetailCourse>findDetailCourseByU(String status);


}
