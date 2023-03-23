package hu.szte.inf.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private int credit;
    @NonNull
    private Semester semester;
    private Grade grade;

    public Course(String name, int credit, Semester semester, Grade grade) {
        this(null, name, credit, semester, grade);
    }
}
