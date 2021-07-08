package com.module.case4.service;

import com.module.case4.model.course.Course;

import java.util.Optional;

public interface ICourseService extends IGeneralService<Course>{
  Course findCourseByName (String keyname);
}
