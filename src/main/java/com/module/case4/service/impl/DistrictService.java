package com.module.case4.service.impl;

import com.module.case4.model.course.District;
import com.module.case4.repository.IDistrictRepository;
import com.module.case4.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DistrictService implements IDistrictService {
    @Autowired
   private IDistrictRepository RepoDistrict;

    @Override
    public Iterable<District> getAll() {
       return RepoDistrict.findAll();
    }

    @Override
    public Optional<District> getByID(Long id) {
    return RepoDistrict.findById(id);
    }

    @Override
    public District save(District district) {
        return RepoDistrict.save(district);
    }

    @Override
    public void delete(Long id) {
RepoDistrict.deleteById(id);
    }
}
