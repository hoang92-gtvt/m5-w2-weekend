package com.module.case4.service;

import java.util.List;
import java.util.Optional;

public interface IGarenalSevice<T> {
    List<T> getAll();
    Optional<T> getOne(Long id);
    void save (T t);
    void delete(Long id);
}