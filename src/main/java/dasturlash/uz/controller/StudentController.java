package dasturlash.uz.controller;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO studentDTO) {
        studentService.create(studentDTO);
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentEntity>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long studentId) {
        StudentEntity student = studentService.getStudentById(studentId);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setAge(student.getAge());
        studentDTO.setGender(student.getGender());
        studentDTO.setLevel(student.getLevel());
        return ResponseEntity.ok(studentDTO);
    }

    @PutMapping("/update/{id}")
    public Boolean update(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO) {
        return studentService.update(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Long studentId) {
        return studentService.delete(studentId);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<StudentDTO>> findAllByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(studentService.getStudentByName(name));
    }

    @GetMapping("/by-surname/{surname}")
    public ResponseEntity<List<StudentDTO>> findAllBySurname(@PathVariable("surname") String surname) {
        return ResponseEntity.ok(studentService.getStudentBySurname(surname));
    }
    @GetMapping("/by-age/{age}")
    public ResponseEntity<List<StudentDTO>> findAllByAge(@PathVariable("age") Integer age) {
        return ResponseEntity.ok(studentService.getStudentByAge(age));
    }

    @GetMapping("/by-level/{level}")
    public ResponseEntity<List<StudentDTO>> findAllByLevel(@PathVariable("level") Integer level) {
        return ResponseEntity.ok(studentService.getStudentByLevel(level));
    }

    @GetMapping("/by-gender/{gender}")
    public ResponseEntity<List<StudentDTO>> findAllByGender(@PathVariable("gender") String gender) {
        return ResponseEntity.ok(studentService.getStudentByGender(gender));
    }

    @GetMapping("/by-date/{date}")
    public ResponseEntity<List<StudentDTO>> findAllByDate(@PathVariable("date") String date) {
        return ResponseEntity.ok(studentService.getStudentByCreatedDate(date));
    }

    @GetMapping("/between-dates/{from}/{to}")
    public ResponseEntity<List<StudentDTO>> findAllByCreatedDateBetween(@PathVariable("from") String from,
                                                                        @PathVariable("to") String to) {
        return ResponseEntity.ok(studentService.getStudentByCreatedDateBetween(from, to));
    }
}


