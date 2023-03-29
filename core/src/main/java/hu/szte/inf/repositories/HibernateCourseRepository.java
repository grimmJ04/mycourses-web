package hu.szte.inf.repositories;

import hu.szte.inf.models.Course;

public class HibernateCourseRepository extends BaseRepository<Course, Long> implements IHibernateCourseRepository {

    public HibernateCourseRepository() {
        super(Course.class);
    }
}
