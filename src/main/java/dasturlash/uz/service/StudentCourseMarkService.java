package dasturlash.uz.service;

import dasturlash.uz.dto.StudentCourseMarkDTO;
import dasturlash.uz.entity.StudentCourseMarkEntity;
import dasturlash.uz.repository.CourseRepository;
import dasturlash.uz.repository.StudentCourseMarkRepository;
import dasturlash.uz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentCourseMarkService {

    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public StudentCourseMarkEntity create(StudentCourseMarkDTO studentCourseMarkDTO) {
        if (studentCourseMarkDTO.getStudentId() == null
                || studentCourseMarkDTO.getCourseId() == null) {
            return null;
        }
        StudentCourseMarkEntity studentCourseMarkEntity = new StudentCourseMarkEntity();
        studentCourseMarkEntity.setStudentId(studentCourseMarkDTO.getStudentId());
        studentCourseMarkEntity.setCourseId(studentCourseMarkDTO.getCourseId());
        studentCourseMarkEntity.setMark(studentCourseMarkDTO.getMark());
        studentCourseMarkEntity.setCreatedDate(LocalDateTime.now());
        return studentCourseMarkRepository.save(studentCourseMarkEntity);
    }

    public Boolean update(Long id, StudentCourseMarkDTO studentCourseMarkDTO) {
        if (studentCourseMarkDTO.getStudentId() == null
                || studentCourseMarkDTO.getCourseId() == null) {
            return false;
        }
        StudentCourseMarkEntity studentCourseMarkEntity = studentCourseMarkRepository.findById(id).orElse(null);
        if (studentCourseMarkEntity == null) {
            return false;
        }
        studentCourseMarkEntity.setMark(studentCourseMarkDTO.getMark());
        studentCourseMarkRepository.save(studentCourseMarkEntity);
        return true;
    }

    public StudentCourseMarkEntity findById(Long id) {
        return studentCourseMarkRepository.findById(id).orElse(null);
    }

    public StudentCourseMarkEntity findByIdWithDetail(Long id) {
        StudentCourseMarkEntity studentCourseMarkEntity = studentCourseMarkRepository.findById(id).orElse(null);
        if (studentCourseMarkEntity == null) {
            return null;
        }
        studentCourseMarkEntity.setStudent(studentRepository.findById(studentCourseMarkEntity.getStudentId()).orElse(null));
        studentCourseMarkEntity.setCourse(courseRepository.findById(studentCourseMarkEntity.getCourseId()).orElse(null));
        return studentCourseMarkEntity;
    }

    public Boolean deleteById(Long id) {
        studentCourseMarkRepository.deleteById(id);
        return true;
    }

    public List<StudentCourseMarkDTO> findAll() {
        List<StudentCourseMarkDTO> studentCourseMarkDTOList = new LinkedList<>();
        studentCourseMarkRepository.findAll().forEach(studentCourseMarkEntity -> {
            studentCourseMarkDTOList.add(toDTO(studentCourseMarkEntity));
        });
        return studentCourseMarkDTOList;
    }

    public List<StudentCourseMarkDTO> getMarkByDate(Long studentId, String date) {
        LocalDateTime temp = LocalDateTime.parse(date);
        List<StudentCourseMarkDTO> studentCourseMarkDTOList = new LinkedList<>();
        studentCourseMarkRepository.findStudentCoursesByStudentIdAndCreatedDate(studentId, temp)
                .forEach(entity -> {
                    studentCourseMarkDTOList.add(toDTO(entity));
                });
        return studentCourseMarkDTOList;
    }

    public List<StudentCourseMarkDTO> getMarkBetweenDate(Long studentId, String start, String end) {
        LocalDateTime from = LocalDateTime.parse(start);
        LocalDateTime to = LocalDateTime.parse(end);
        List<StudentCourseMarkDTO> studentCourseMarkDTOList = new LinkedList<>();
        studentCourseMarkRepository.findStudentCoursesByStudentIdAndCreatedDateBetween(studentId, from, to)
                .forEach(entity -> {
                    studentCourseMarkDTOList.add(toDTO(entity));
                });
        return studentCourseMarkDTOList;
    }

    private StudentCourseMarkDTO toDTO(StudentCourseMarkEntity studentCourseMarkEntity) {
        StudentCourseMarkDTO studentCourseMarkDTO = new StudentCourseMarkDTO();
        studentCourseMarkDTO.setStudentId(studentCourseMarkEntity.getStudentId());
        studentCourseMarkDTO.setCourseId(studentCourseMarkEntity.getCourseId());
        studentCourseMarkDTO.setMark(studentCourseMarkEntity.getMark());
        return studentCourseMarkDTO;
    }

}
