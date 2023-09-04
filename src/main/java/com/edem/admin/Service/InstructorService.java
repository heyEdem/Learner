package com.edem.admin.Service;

import com.edem.admin.entity.Instructor;

import java.util.List;

public interface InstructorService {
    Instructor loadInstructorById(Long instructorId);

    List <Instructor> findInstructorsByName(String name);

    Instructor findInstructorByEmail(String email);

    Instructor createInstructor(String firstName, String lastName, String summary, String email, String password);

    Instructor updateInstructor(Instructor instructor);

    List <Instructor> fetchAllInstructors();

    void removeInstructor(Long instructorId);

}
