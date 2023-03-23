package hu.szte.inf.repositories;

import hu.szte.inf.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHibernateCourseRepository extends CrudRepository<Course, Long> {
}
