package com.manytomany.controller;

import com.manytomany.domain.StudentDomain;
import com.manytomany.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/initiate")
    public ResponseEntity<StudentDomain> initiateStudent(@RequestBody StudentDomain students){
        StudentDomain studentDomain =  studentService.initiateStudent(students);
        return new ResponseEntity<>(studentDomain, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDomain> getStudentWithCourses(@PathVariable Long studentId) {
        StudentDomain studentDomain = studentService.getStudentWithCoursesById(studentId);
        return new ResponseEntity<>(studentDomain, HttpStatus.OK);
    }
    @GetMapping("/course/{courseName}")
    public ResponseEntity<List<StudentDomain>> getCourseWithStudentInfo(@PathVariable String courseName){
        List<StudentDomain> studentDomain = studentService.getCourseWithStudentInfo(courseName);
        return new ResponseEntity<>(studentDomain,HttpStatus.OK);
    }

    @GetMapping("/studentInfo")
    public ResponseEntity<List<StudentDomain>> getAllStudent(){
        List<StudentDomain> studentDomain = studentService.getAllStudentList();
        return new ResponseEntity<>(studentDomain,HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("{studentId}")
    public ResponseEntity<String> deleteStudentInfo(@PathVariable Long studentId){
        studentService.deleteStudentInfo(studentId);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDomain> updateStudent(@PathVariable Long studentId, @RequestBody StudentDomain updatedStudent) {
        ResponseEntity<StudentDomain> response = studentService.updateStudent(studentId, updatedStudent);
        return response;
    }
}


//POST:http://localhost:8080/api/v1/student/initiate
// {
//    "studentName": ["kannan"],
//    "courseNames": ["java", "python","C"]
//}