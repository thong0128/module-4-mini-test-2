package com.codegym.minitest2.service;

import com.codegym.minitest2.model.Category;
import com.codegym.minitest2.model.CategoryStat;

public interface ICategoryService extends IGenerateService<Category>{
    Iterable<CategoryStat> countTaskAndTotalAmountByCategory();
}
