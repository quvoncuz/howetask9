package dasturlash.uz.controller;

import dasturlash.uz.dto.CourseDTO;
import dasturlash.uz.entity.CourseEntity;
import dasturlash.uz.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseEntity> create(@RequestBody CourseDTO courseDTO) {
        CourseEntity course = courseService.create(courseDTO);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") Long id) {
        CourseDTO courseDTO = courseService.getCourseById(id);
        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getCourses();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public Boolean update(@PathVariable("id") Long id, @RequestBody CourseDTO courseDTO) {
        return courseService.update(id, courseDTO);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return courseService.delete(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CourseDTO>> getCoursesByName(@PathVariable("name") String name) {
        List<CourseDTO> courseByName = courseService.getCourseByName(name);
        return ResponseEntity.ok(courseByName);
    }

    @GetMapping("/duration/{duration}")
    public ResponseEntity<List<CourseDTO>> getCoursesByDuration(@PathVariable("duration") Integer duration) {
        List<CourseDTO> courseByName = courseService.getCourseByDuration(duration);
        return ResponseEntity.ok(courseByName);
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<CourseDTO>> getCoursesByPrice(@PathVariable("price") Double price) {
        List<CourseDTO> courseByName = courseService.getCourseByPrice(price);
        return ResponseEntity.ok(courseByName);
    }

    @GetMapping("/{from}/{to}")
    public ResponseEntity<List<CourseDTO>> getCoursesByPriceBetween(@PathVariable("from") Double price1,
                                                                    @PathVariable("to") Double price2) {
        List<CourseDTO> courseByName = courseService.getCourseByPriceBetween(price1, price2);
        return ResponseEntity.ok(courseByName);
    }


    @GetMapping("/dates/{from}/{to}")
    public ResponseEntity<List<CourseDTO>> getCoursesByDatesBetween(@PathVariable("from") String from,
                                                                    @PathVariable("to") String to) {
        List<CourseDTO> courseByName = courseService.getCourseByCreatedDateBetween(from, to);
        return ResponseEntity.ok(courseByName);
    }




}
