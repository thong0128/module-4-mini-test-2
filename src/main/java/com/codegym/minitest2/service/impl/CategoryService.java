package com.codegym.minitest2.service.impl;

import com.codegym.minitest2.model.Category;
import com.codegym.minitest2.model.CategoryStat;
import com.codegym.minitest2.repository.ICategoryRepository;
import com.codegym.minitest2.service.ICategoryService;
import com.codegym.minitest2.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private ITaskService taskService;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

//    @Override
//    public void remove(Long id) {
//        categoryRepository.deleteById(id);
//    }
    @Override
    public void remove(Long id) {
        categoryRepository.deleteCategory(id);
    }

    @Override
    public Iterable<CategoryStat> countTaskAndTotalAmountByCategory() {
        return categoryRepository.countTaskAndTotalAmountByCategory();
    }
}
