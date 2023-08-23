package com.manytomany.repositary;

import com.manytomany.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepo extends JpaRepository<StudentCourse, Long> {
    @Modifying
    @Query("DELETE FROM StudentCourse sc WHERE sc.student.id = :studentId")
    void deleteByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT sc.student.id FROM StudentCourse sc WHERE sc.course.id = :courseId")
    List<Long> findByCourseId(Long courseId);
}
