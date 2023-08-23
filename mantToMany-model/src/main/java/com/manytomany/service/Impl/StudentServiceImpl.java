package com.manytomany.service.Impl;

import com.manytomany.domain.StudentDomain;
import com.manytomany.model.Course;
import com.manytomany.model.Student;
import com.manytomany.repositary.CourseRepo;
import com.manytomany.repositary.StudentCourseRepo;
import com.manytomany.repositary.StudentRepo;
import com.manytomany.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepository;

    @Autowired
    private StudentCourseRepo studentCourseRepo;

    @Override
    public StudentDomain initiateStudent(StudentDomain studentDomain) {
        try {
            Set<Course> courses = new HashSet<>();
            Set<Student> students = new HashSet<>();
            for(String name : studentDomain.getStudentName()){
                Student student = new Student();
                student.setStudentName(name);
                students.add(student);
            }
            for (String courseName : studentDomain.getCourseNames()) {
                Course course = courseRepository.findByCourseName(courseName);
                if(course == null) {
                    course = new Course();
                    course.setCourseName(courseName);
                    courseRepository.save(course);
                }
                courses.add(course);
            }
            for(Student student : students){
                student.setCourses(courses);
                studentRepo.save(student);
            }
            return studentDomain;
        } catch (Exception e) {
            e.printStackTrace();
            return studentDomain;
        }
    }

    @Override
    public StudentDomain getStudentWithCoursesById(Long id) {
        Optional<Student> students = studentRepo.findById(id);
        StudentDomain studentDomain = new StudentDomain();
        Set<Course> courseSet = new HashSet<>();
        if(students.isPresent()) {
            Student student = students.get();
            setCourse(student,studentDomain,courseSet);
        }
            return studentDomain;
    }

    @Override
    public List<StudentDomain> getAllStudentList() {
        List<StudentDomain> studentDomainList = new ArrayList<>();
        List<Student> studentList = studentRepo.findAll();
        for (Student student : studentList){
            Set<Course> courseSet = new HashSet<>();
            StudentDomain studentDomain = new StudentDomain();
            setCourse(student,studentDomain,courseSet);
            studentDomainList.add(studentDomain);
        }
        return studentDomainList;
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteStudentInfo(Long studentId) {
        Optional<Student> studentOptional = studentRepo.findById(studentId);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            studentCourseRepo.deleteByStudentId(studentId);
            studentRepo.delete(student);
        return ResponseEntity.ok("Student and associated courses deleted successfully.");
    } else {
        return ResponseEntity.notFound().build();
    }
    }

    @Override
    public List<StudentDomain> getCourseWithStudentInfo(String courseName) {
        List<StudentDomain> studentDomainList = new ArrayList<>();
        Course course = courseRepository.findByCourseName(courseName);
        if(course != null){
            List<Long> studentId = studentCourseRepo.findByCourseId(course.getId());
            for(Long sId : studentId){
                Optional<Student> studentOptional = studentRepo.findById(sId);
                if(studentOptional.isPresent()) {
                    Student student = studentOptional.get();
                    StudentDomain studentDomain = new StudentDomain();
                    studentDomain.setCourseNames(Collections.singletonList(course.getCourseName()));
                    studentDomain.setStudentName(Collections.singletonList(student.getStudentName()));
                    studentDomainList.add(studentDomain);
                }
            }
        }
        return studentDomainList;
    }




    private void setCourse(Student student, StudentDomain studentDomain,Set<Course> courseSet) {
        List<Long> courseId = courseRepository.getByCourseIdUsingStudentId(student.getId());
        for(Long cId : courseId ) {
            Optional<Course> optionalCourse = courseRepository.findById(cId);
            optionalCourse.ifPresent(course -> {
                Course newCourse = new Course();
                newCourse.setCourseName(course.getCourseName());
                courseSet.add(newCourse);
            });
        }
        studentDomain.setStudentName(Collections.singletonList(student.getStudentName()));
        student.setCourses(courseSet);
        List<String> courseNames = student.getCourses().stream()
                .map(Course::getCourseName)
                .collect(Collectors.toList());
        studentDomain.setCourseNames(courseNames);
    }




    @Override
    public ResponseEntity<StudentDomain> updateStudent(Long studentId, StudentDomain updatedStudent) {
        Optional<Student> studentOptional = studentRepo.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            if (updatedStudent.getStudentName() != null && !updatedStudent.getStudentName().isEmpty()) {
                student.setStudentName(updatedStudent.getStudentName().get(0));
            }
            // Clear existing courses and add updated courses
            student.getCourses().clear();
            if (updatedStudent.getCourseNames() != null && !updatedStudent.getCourseNames().isEmpty()) {
                for (String courseName : updatedStudent.getCourseNames()) {
                    Course course = courseRepository.findByCourseName(courseName);
                    if (course == null) {
                        course = new Course();
                        course.setCourseName(courseName);
                        courseRepository.save(course);
                    }
                    student.getCourses().add(course);
                }
            }

            // Saving the updated student
            studentRepo.save(student);

            // Constructing and returning the updated StudentDomain
            StudentDomain updatedDomain = new StudentDomain();
            updatedDomain.setStudentName(Collections.singletonList(student.getStudentName()));
            updatedDomain.setCourseNames(student.getCourses().stream()
                    .map(Course::getCourseName)
                    .collect(Collectors.toList()));

            return ResponseEntity.ok(updatedDomain);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
