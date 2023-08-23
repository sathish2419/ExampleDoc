package com.manytomany.repositary;

import com.manytomany.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {

    Course findByCourseName(String courseName);

    @Query("SELECT sc.course.id FROM StudentCourse sc WHERE sc.student.id = :studentId")
    List<Long> getByCourseIdUsingStudentId(Long studentId);
}
