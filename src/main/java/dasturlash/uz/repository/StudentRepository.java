package dasturlash.uz.repository;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    List<StudentEntity> getStudentByNameIgnoreCase(String name);
    List<StudentEntity> getStudentBySurnameIgnoreCase(String surname);
    List<StudentEntity> getStudentByLevel(Integer level);
    List<StudentEntity> getStudentByGenderIgnoreCase(String gender);
    List<StudentEntity> getStudentByAge(Integer age);
    List<StudentEntity> getStudentByCreatedDate(LocalDateTime createdDate);

    List<StudentEntity> getStudentByCreatedDateBetween(LocalDateTime from, LocalDateTime to);

}
