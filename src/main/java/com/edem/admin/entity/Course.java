package com.edem.admin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id",nullable = false)
    private Long courseId;

    @Basic
    @Column(name = "course_name", nullable = false,length = 45)
    private String courseName;

    @Basic
    @Column(name ="course_duration",nullable = false,length = 45)
    private String courseDuration;

    @Basic
    @Column(name ="course_description",nullable = false, length = 65)
    private String courseDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id",nullable = false)
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="enrolled_in", joinColumns = {@JoinColumn(name="course_id")},
    inverseJoinColumns = {@JoinColumn(name="student_id")})
    private Set<Student> students = new HashSet<>();

    public Course(String java, String s, String introductionToJava, Instructor instructor) {

    }

    public void assignStudentToCourse (Student student){
        this.students.add(student);
        student.getCourses().add(this);
    }

    public void removeStudentFromCourse (Student student){
        this.students.remove(student);
        student.getCourses().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId.equals(course.courseId) && Objects.equals(courseName, course.courseName) && Objects.equals(courseDuration, course.courseDuration) && Objects.equals(courseDescription, course.courseDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseDuration, courseDescription);
    }


    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDuration='" + courseDuration + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                '}';
    }
}
