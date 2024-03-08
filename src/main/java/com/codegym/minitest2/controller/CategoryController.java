package com.codegym.minitest2.controller;

import com.codegym.minitest2.model.Category;
import com.codegym.minitest2.model.CategoryStat;
import com.codegym.minitest2.model.Task;
import com.codegym.minitest2.service.ICategoryService;
import com.codegym.minitest2.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ITaskService taskService;
    @GetMapping
    public ModelAndView listTask() {
        ModelAndView modelAndView = new ModelAndView("/category/list");
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("category") Category category,
                         RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Create new category successfully");
        return "redirect:/categories";
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/category/update");
            modelAndView.addObject("category", category.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("category") Category category,
                         RedirectAttributes redirect) {
        categoryService.save(category);
        redirect.addFlashAttribute("message", "Update category successfully");
        return "redirect:/categories";
    }
    @GetMapping("/view-category/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(!categoryOptional.isPresent()){
            return new ModelAndView("/error_404");
        }
        Iterable<Task> tasks = taskService.findAllByCategory(categoryOptional.get());
        ModelAndView modelAndView = new ModelAndView("/task/list");
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        categoryService.remove(id);
        redirect.addFlashAttribute("message", "Delete category successfully");
        return "redirect:/categories";
    }
    @GetMapping("/stat")
    public ModelAndView stat() {
        ModelAndView modelAndView = new ModelAndView("/category/stat");
        Iterable<CategoryStat> stats = categoryService.countTaskAndTotalAmountByCategory();
        modelAndView.addObject("stats", stats);
        return modelAndView;
    }
}
