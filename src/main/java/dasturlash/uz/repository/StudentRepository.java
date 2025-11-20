package dasturlash.uz.repository;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.mapper.StudentInfoMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Long>, PagingAndSortingRepository<StudentEntity, Long> {
    @Query
    List<StudentEntity> getStudentByNameIgnoreCase(String name);
    List<StudentEntity> getStudentBySurnameIgnoreCase(String surname);
    List<StudentEntity> getStudentByLevel(Integer level);
    List<StudentEntity> getStudentByGenderIgnoreCase(String gender);
    List<StudentEntity> getStudentByAge(Integer age);
    List<StudentEntity> getStudentByCreatedDate(LocalDateTime createdDate);

    List<StudentEntity> getStudentByCreatedDateBetween(LocalDateTime from, LocalDateTime to);

    @Query("select id, name, surname From StudentEntity  where age =?1 ")
    List<Object[]> findShortInfo1(Integer age);


    @Query("select id as id, name as name, surname as surname From StudentEntity  where age =?1 ")
    List<StudentInfoMapper> findShortInfo(Integer age);

}
