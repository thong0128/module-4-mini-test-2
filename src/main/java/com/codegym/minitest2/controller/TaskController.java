package com.codegym.minitest2.controller;

import com.codegym.minitest2.model.Category;
import com.codegym.minitest2.model.Task;
import com.codegym.minitest2.service.ICategoryService;
import com.codegym.minitest2.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }
    @GetMapping
    public ModelAndView listTask(Pageable pageable) {
        Page<Task> tasks = taskService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/task/list");
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/task/create");
        modelAndView.addObject("task", new Task());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("task") Task task,
                         RedirectAttributes redirectAttributes) {
        taskService.save(task);
        redirectAttributes.addFlashAttribute("message", "Create new task successfully");
        return "redirect:/tasks";
    }
}
