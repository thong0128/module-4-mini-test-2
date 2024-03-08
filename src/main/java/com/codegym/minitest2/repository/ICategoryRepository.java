package com.codegym.minitest2.repository;

import com.codegym.minitest2.model.Category;
import com.codegym.minitest2.model.CategoryStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends CrudRepository<Category, Long> {
    @Query(nativeQuery = true, value = "select c.name as name, count(t.name) as totalTask, sum(t.amount) as totalAmount from category c left join minitest2.tasks t on c.id = t.category_id group by c.id")
    Iterable<CategoryStat> countTaskAndTotalAmountByCategory();
}
