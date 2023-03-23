package hu.szte.inf.controllers;

import hu.szte.inf.models.Course;
import hu.szte.inf.repositories.IHibernateCourseRepository;
import hu.szte.inf.utils.Functional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

    private final IHibernateCourseRepository repository;

    public CourseController(IHibernateCourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = {"/create", "new"})
    public ModelAndView create() {
        return new ModelAndView("course/create", "model", new Course());
    }

    @PostMapping(value = {"/create", "new"})
    private String create(Course model) {
        repository.save(model);
        return "redirect:read";
    }

    @GetMapping(value = {"read", "view"})
    private ModelAndView read() {
        return new ModelAndView("course/read", "models", Functional.iterableToList(repository.findAll()));
    }

    @GetMapping(value = {"update", "edit"})
    private ModelAndView update(@RequestParam Long id) {
        var model = repository.findById(id);
        return model
                .map(course -> new ModelAndView("course/update", "model", course))
                .orElseGet(() -> new ModelAndView("redirect:read", HttpStatus.BAD_REQUEST));
    }

    @PostMapping(value = {"update", "edit"})
    private String update(Course model) {
        repository.save(model);
        return "redirect:read";
    }

    @GetMapping(value = {"delete", "remove"})
    private ModelAndView delete(@RequestParam Long id) {
        var model = repository.findById(id);
        return model
                .map(course -> new ModelAndView("course/delete", "model", course))
                .orElseGet(() -> new ModelAndView("redirect:read", HttpStatus.BAD_REQUEST));
    }

    @PostMapping(value = {"delete", "remove"})
    private String delete(long id) {
        repository.deleteById(id);
        return "redirect:read";
    }
}
