package hu.szte.inf;

import hu.szte.inf.repositories.HibernateCourseRepository;
import hu.szte.inf.repositories.IHibernateCourseRepository;
import hu.szte.inf.utils.db.DbInitializer;

public class Main {

    private final static IHibernateCourseRepository repository = new HibernateCourseRepository();

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        repository.saveAll(DbInitializer.getDefaultCourses(20));
    }
}
