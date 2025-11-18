package dasturlash.uz.controller;

import dasturlash.uz.dto.StudentCourseMarkDTO;
import dasturlash.uz.entity.StudentCourseMarkEntity;
import dasturlash.uz.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-course")
public class StudentCourseMarkController {

    @Autowired
    private StudentCourseMarkService studentCourseMarkService;

    @PostMapping("/create")
    public ResponseEntity<StudentCourseMarkEntity> create(@RequestBody StudentCourseMarkDTO studentCourseMarkDTO) {
        StudentCourseMarkEntity studentCourseMarkEntity = studentCourseMarkService.create(studentCourseMarkDTO);
        return ResponseEntity.ok(studentCourseMarkEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentCourseMarkEntity> findById(@PathVariable("id") Long id){
        StudentCourseMarkEntity studentCourseMarkEntity = studentCourseMarkService.findById(id);
        return ResponseEntity.ok(studentCourseMarkEntity);
    }

    @PutMapping("/update/{id}")
    public Boolean update(@PathVariable("id") Long id,
                          @RequestBody StudentCourseMarkDTO studentCourseMarkDTO) {
        return studentCourseMarkService.update(id, studentCourseMarkDTO);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<StudentCourseMarkEntity> findByIdWithDetail(@PathVariable("id") Long id){
        StudentCourseMarkEntity byIdWithDetail = studentCourseMarkService.findByIdWithDetail(id);
        return ResponseEntity.ok(byIdWithDetail);
    }
    @GetMapping("/list")
    public ResponseEntity<List<StudentCourseMarkDTO>> findAll(){
        List<StudentCourseMarkDTO> studentCourseMarkDTOList = studentCourseMarkService.findAll();
        return ResponseEntity.ok(studentCourseMarkDTOList);
    }
}
