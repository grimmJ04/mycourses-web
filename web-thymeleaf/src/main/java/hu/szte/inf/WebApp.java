package hu.szte.inf;

import hu.szte.inf.repositories.IHibernateCourseRepository;
import hu.szte.inf.utils.db.DbInitializer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class WebApp {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }
}

@Component
class DataLoader implements ApplicationRunner {

    private final IHibernateCourseRepository courseRepository;

    public DataLoader(IHibernateCourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        courseRepository.saveAll(DbInitializer.getDefaultCourses(20));
    }
}
