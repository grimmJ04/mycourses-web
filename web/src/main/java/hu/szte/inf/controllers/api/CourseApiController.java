package hu.szte.inf.controllers.api;

import hu.szte.inf.models.Course;
import hu.szte.inf.repositories.IHibernateCourseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController(value = "/api/course")
public class CourseApiController {

    final IHibernateCourseRepository repository;

    public CourseApiController(IHibernateCourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/list")
    public List<Course> getList() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping(value = "/count")
    public long getCount() {
        return repository.count();
    }
}
