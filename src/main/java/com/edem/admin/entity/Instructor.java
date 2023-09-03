package com.edem.admin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "instructor_Id", nullable = false)
    private Long instructorId;

    @Basic
    @Column(name = "first_name", nullable = false,length = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false,length = 45)
    private String lastName;

    @Basic
    @Column(name = "summary", nullable = false,length = 65)
    private String summary;

    @OneToMany(mappedBy = "instructor",fetch = FetchType.LAZY)
    private Set<Course> courses = new HashSet<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",nullable = false)
    private User user;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return instructorId.equals(that.instructorId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorId, firstName, lastName, summary);
    }

    public Instructor(String firstName, String lastName, String summary, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.summary = summary;
        this.user = user;
    }

    public Instructor(Long instructorId, String firstName, String lastName, String summary) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.summary = summary;
    }
}
