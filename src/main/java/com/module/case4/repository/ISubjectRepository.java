package com.module.case4.repository;

import com.module.case4.model.course.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepository extends JpaRepository<Subject,Long> {

}
