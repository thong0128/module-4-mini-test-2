package com.codegym.minitest2.service;

import com.codegym.minitest2.model.Category;
import com.codegym.minitest2.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITaskService extends IGenerateService<Task> {
    Iterable<Task> findAllByCategory(Category category);
    Page<Task> findAll(Pageable pageable);
    Page<Task> findAllByNameContaining(Pageable pageable, String name);
}
