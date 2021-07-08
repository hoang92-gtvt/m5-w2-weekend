package com.module.case4.service.impl;

import com.module.case4.model.course.Subject;
import com.module.case4.repository.ISubjectRepository;
import com.module.case4.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SubjectService implements ISubjectService {
    @Autowired
    private ISubjectRepository subjectRepository;

    @Override
    public Iterable<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> getByID(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void delete(Long id) {
subjectRepository.deleteById(id);
    }
}
