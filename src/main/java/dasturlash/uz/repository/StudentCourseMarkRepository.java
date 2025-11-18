package dasturlash.uz.repository;


import dasturlash.uz.entity.StudentCourseMarkEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, Long> {
    List<StudentCourseMarkEntity> findStudentCoursesByStudentIdAndCreatedDate(Long studentId, LocalDateTime date);
    List<StudentCourseMarkEntity> findStudentCoursesByStudentIdAndCreatedDateBetween(Long studentId, LocalDateTime start, LocalDateTime end);
}
