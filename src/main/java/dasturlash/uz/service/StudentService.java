package dasturlash.uz.service;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.mapper.StudentInfoMapper;
import dasturlash.uz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO create(StudentDTO studentDTO) {
        StudentEntity student = new StudentEntity();
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setAge(studentDTO.getAge());
        student.setLevel(studentDTO.getLevel());
        student.setGender(studentDTO.getGender());
        student.setCreatedDate(LocalDateTime.now());
        studentRepository.save(student);
        return studentDTO;
    }

    public List<StudentEntity> findAll() {
        Iterable<StudentEntity> studentList = studentRepository.findAll();
        return (List<StudentEntity>) studentList;
    }

    public StudentEntity getStudentById(Long studentId) {
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        return studentEntity.orElse(null);
    }

    public Boolean update(Long studentId, StudentDTO studentDTO) {
        if (studentDTO.getName() == null
                || studentDTO.getSurname() == null
                || studentDTO.getAge() == null
                || studentDTO.getLevel() == null
                || studentDTO.getGender() == null) {
            return false;
        }
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        if (studentEntity.isPresent()) {
            StudentEntity student = studentEntity.get();
            student.setName(studentDTO.getName());
            student.setSurname(studentDTO.getSurname());
            student.setAge(studentDTO.getAge());
            student.setLevel(studentDTO.getLevel());
            student.setGender(studentDTO.getGender());
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    public Boolean delete(Long studentId) {
        studentRepository.deleteById(studentId);
        return true;
    }

    public List<StudentDTO> getStudentByName(String name) {
        List<StudentEntity> studenList = studentRepository.getStudentByNameIgnoreCase(name);
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (StudentEntity student : studenList) {
            studentDTOList.add(toDTO(student));
        }
        return studentDTOList;
    }

    public List<StudentDTO> getStudentBySurname(String surname) {
        List<StudentEntity> studenList = studentRepository.getStudentBySurnameIgnoreCase(surname);
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (StudentEntity student : studenList) {
            studentDTOList.add(toDTO(student));
        }
        return studentDTOList;
    }

    public List<StudentDTO> getStudentByAge(Integer age) {
        List<StudentEntity> studenList = studentRepository.getStudentByAge(age);
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (StudentEntity student : studenList) {
            studentDTOList.add(toDTO(student));
        }
        return studentDTOList;
    }

    public List<StudentDTO> getStudentByLevel(Integer level) {
        List<StudentEntity> studenList = studentRepository.getStudentByLevel(level);
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (StudentEntity student : studenList) {
            studentDTOList.add(toDTO(student));
        }
        return studentDTOList;
    }

    public List<StudentDTO> getStudentByGender(String gender) {
        List<StudentEntity> studenList = studentRepository.getStudentByGenderIgnoreCase(gender);
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (StudentEntity student : studenList) {
            studentDTOList.add(toDTO(student));
        }
        return studentDTOList;
    }

    public List<StudentDTO> getStudentByCreatedDate(String date) {
        List<StudentEntity> studenList = studentRepository.getStudentByCreatedDate(LocalDateTime.parse(date));
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (StudentEntity student : studenList) {
            studentDTOList.add(toDTO(student));
        }
        return studentDTOList;
    }

    public List<StudentDTO> getStudentByCreatedDateBetween(String fromDate, String toDate) {
        List<StudentEntity> studenList = studentRepository.getStudentByCreatedDateBetween(LocalDateTime.parse(fromDate), LocalDateTime.parse(toDate));
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (StudentEntity student : studenList) {
            studentDTOList.add(toDTO(student));
        }
        return studentDTOList;
    }

    public List<StudentDTO> findShortInfo(Integer age) {
        List<StudentInfoMapper> entityList = studentRepository.findShortInfo(age);
        List<StudentDTO> dtoList = new LinkedList<>();

        for (StudentInfoMapper mapper : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setName(mapper.getName());
            dto.setSurname(mapper.getSurname());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public PageImpl<StudentDTO> pagination(int page , int size) {
        // page = 1, size = 30
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by("age").descending());
        Page<StudentEntity> pageResult = studentRepository.findAll(pageRequest);
        // content - select * from student limit :size offset :page
        // totalCount - select count(*) from student

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : pageResult.getContent()) {
            StudentDTO dto = new StudentDTO();
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dtoList.add(dto);
        }

        long totalCount = pageResult.getTotalElements();

        return new PageImpl<>(dtoList, pageRequest, totalCount);
    }



    private StudentDTO toDTO(StudentEntity studentEntity) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(studentEntity.getName());
        studentDTO.setSurname(studentEntity.getSurname());
        studentDTO.setAge(studentEntity.getAge());
        studentDTO.setLevel(studentEntity.getLevel());
        studentDTO.setGender(studentEntity.getGender());
        return studentDTO;
    }
}
