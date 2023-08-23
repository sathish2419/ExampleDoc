package com.manytomany.service;

import com.manytomany.domain.StudentDomain;
import com.manytomany.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    StudentDomain initiateStudent(StudentDomain students);

    StudentDomain getStudentWithCoursesById(Long studentId);

    List<StudentDomain> getAllStudentList();

    ResponseEntity<String > deleteStudentInfo(Long studentId);

    List<StudentDomain> getCourseWithStudentInfo(String courseName);

    ResponseEntity<StudentDomain> updateStudent(Long studentId, StudentDomain updatedStudent);


}
