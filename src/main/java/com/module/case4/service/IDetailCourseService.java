package com.module.case4.service;

import com.module.case4.model.course.DetailCourse;

import java.util.List;

public interface IDetailCourseService extends IGeneralService<DetailCourse>{
    Iterable<DetailCourse> findAllByStatusContaining(String status);
    List<DetailCourse> findDetailByUserStudentAndStatus(String name, String status);
    List<DetailCourse> findDetailByUserStudent(String name);
    List<DetailCourse> findDetailByUserTeacher(String name);
    List<DetailCourse> findDetailByStatus(String status,List<DetailCourse> detailCourses);

}
