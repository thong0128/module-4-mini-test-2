package com.codegym.minitest2.repository;
import java.util.List;

public interface IGenerateRepository<T> {
    List<T> findAll();
    T findById(Long id);
    void save(T t);
    void remove(Long id);
}