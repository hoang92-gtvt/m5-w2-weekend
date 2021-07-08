package com.module.case4.service.impl;

import com.module.case4.model.course.DetailCourse;
import com.module.case4.model.course.Status;
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
            DetailCourse detailCourse = detailCourses1.get(i);
            String name1 =detailCourse.getUserStudent().getName();
            Status status1 = detailCourse.getStatus();
            if (name1.equals(name)
                    && status1.name().equals(status)){
                detailCourses.add(detailCourse);
            }
        }
        return detailCourses;
    }

//lấy ra chi tiết các khoá học trong page sinh viên
    @Override
    public List<DetailCourse> findDetailByUserStudent(String name) {
        List<DetailCourse> detailCourses = new ArrayList<>();
        List<DetailCourse> detailCourses1 = repository.findAll();
        for (int i = 0; i < detailCourses1.size(); i ++){
            if (detailCourses1.get(i).getUserStudent().getName().equals(name)){
                detailCourses.add(detailCourses1.get(i));
            }
        }
        return detailCourses;
    }



//Lấy ra chi tiết các khoá học trong page teacher
    @Override
    public List<DetailCourse> findDetailByUserTeacher(String name) {
        List<DetailCourse> detailCourse = new ArrayList<>();
        List<DetailCourse> detailCourses1 = repository.findAll();
        for (int i = 0; i < detailCourses1.size(); i ++){
            if (detailCourses1.get(i).getUserStudent().equals(name)){
                detailCourse.add(detailCourses1.get(i));
            }
        }
        return detailCourse;
    }

    @Override
    public List<DetailCourse> findDetailByStatus(String status, List<DetailCourse> detailCourses) {
        List<DetailCourse> detailCourses1 = new ArrayList<>();
        for ( int i = 0; i > detailCourses.size(); i++){
            if (detailCourses.get(i).getStatus().equals(status)){
                detailCourses1.add(detailCourses.get(i));
            }
        }
        return detailCourses1;
    }


}
