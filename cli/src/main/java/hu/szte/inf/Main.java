package hu.szte.inf;

import hu.szte.inf.models.Grade;
import hu.szte.inf.repositories.HibernateCourseRepository;
import hu.szte.inf.repositories.IHibernateCourseRepository;
import hu.szte.inf.utils.Functional;
import hu.szte.inf.utils.db.DbInitializer;

import java.util.stream.Collectors;

public class Main {

    private final static IHibernateCourseRepository repository = new HibernateCourseRepository();

    public static void main(String[] args) {
        init();
        findWhereGradeIsSufficient();
    }

    private static void init() {
        repository.saveAll(DbInitializer.getDefaultCourses(20));
    }

    private static void findWhereGradeIsSufficient() {
        var result = Functional.iterableToList(repository.findAll())
                .stream()
                .filter(el -> el.getGrade() == Grade.SUFFICIENT)
                .collect(Collectors.toList());
        result.forEach(el -> System.out.println(el.toString()));
    }
}
