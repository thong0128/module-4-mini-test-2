package com.codegym.minitest2.service.impl;

import com.codegym.minitest2.model.Category;
import com.codegym.minitest2.model.Task;
import com.codegym.minitest2.repository.ITaskRepository;
import com.codegym.minitest2.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Iterable<Task> findAllByCategory(Category category) {
        return taskRepository.findAllByCategory(category);
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Page<Task> findAllByNameContaining(Pageable pageable, String name) {
        return taskRepository.findAllByNameContaining(pageable, name);
    }
}
