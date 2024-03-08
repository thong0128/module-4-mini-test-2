package com.codegym.minitest2.controller;

import com.codegym.minitest2.model.Category;
import com.codegym.minitest2.model.Task;
import com.codegym.minitest2.service.ICategoryService;
import com.codegym.minitest2.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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
    @GetMapping("/search")
    public ModelAndView listTaskSearch(@RequestParam("search") Optional<String> search, Pageable pageable){
        Page<Task> tasks;
        if(search.isPresent()){
            tasks = taskService.findAllByNameContaining(pageable, search.get());
        } else {
            tasks = taskService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/task/list");
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Task> task = taskService.findById(id);
        if (task.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/task/update");
            modelAndView.addObject("task", task.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("customer") Task task,
                         RedirectAttributes redirect) {
        taskService.save(task);
        redirect.addFlashAttribute("message", "Update task successfully");
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        taskService.remove(id);
        redirect.addFlashAttribute("message", "Delete task successfully");
        return "redirect:/tasks";
    }
}
