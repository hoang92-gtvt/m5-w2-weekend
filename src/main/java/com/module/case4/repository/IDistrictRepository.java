package com.module.case4.repository;

import com.module.case4.model.course.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDistrictRepository extends JpaRepository<District,Long> {
}
