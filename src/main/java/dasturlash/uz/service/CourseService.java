package dasturlash.uz.service;

import dasturlash.uz.dto.CourseDTO;
import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.entity.CourseEntity;
import dasturlash.uz.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity create(CourseDTO courseDTO) {
        if (courseDTO.getDuration() == null
                || courseDTO.getName() == null
                || courseDTO.getPrice() == null
                || courseDTO.getPrice() < 0
                || courseDTO.getDuration() < 0
                || courseDTO.getName().isBlank()) {
            return null;
        }
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(courseDTO.getName());
        courseEntity.setPrice(courseDTO.getPrice());
        courseEntity.setDuration(courseDTO.getDuration());
        courseEntity.setCreatedDate(LocalDateTime.now());
        return courseRepository.save(courseEntity);
    }

    public CourseDTO getCourseById(Long id) {
        Optional<CourseEntity> course = courseRepository.findById(id);
        if (course.isPresent()) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setName(course.get().getName());
            courseDTO.setPrice(course.get().getPrice());
            courseDTO.setDuration(course.get().getDuration());
            return courseDTO;
        }
        return null;
    }

    public List<CourseDTO> getCourses() {
        List<CourseDTO> courseDTOList = new LinkedList<>();
        courseRepository.findAll().forEach(courseEntity -> {
            courseDTOList.add(toDTO(courseEntity));
        });
        return courseDTOList;
    }

    public Boolean update(Long id, CourseDTO courseDTO) {
        if (courseDTO.getDuration() == null
                || courseDTO.getName() == null
                || courseDTO.getPrice() == null
                || courseDTO.getPrice() < 0
                || courseDTO.getDuration() < 0
                || courseDTO.getName().isBlank()) {
            return false;
        }
        Optional<CourseEntity> course = courseRepository.findById(id);
        if (course.isPresent()) {
            CourseEntity courseEntity = course.get();
            courseEntity.setName(courseDTO.getName());
            courseEntity.setPrice(courseDTO.getPrice());
            courseEntity.setDuration(courseDTO.getDuration());
            courseRepository.save(courseEntity);
            return true;
        }
        return false;
    }

    public Boolean delete(Long id) {
        courseRepository.deleteById(id);
        return true;
    }

    public List<CourseDTO> getCourseByName(String courseName) {
        List<CourseDTO> studentDTOList = new LinkedList<>();
        for (CourseEntity course : courseRepository.getCourseByName(courseName)) {
            studentDTOList.add(toDTO(course));
        }
        return studentDTOList;
    }
    public List<CourseDTO> getCourseByDuration(Integer duration) {
        List<CourseDTO> studentDTOList = new LinkedList<>();
        for (CourseEntity course : courseRepository.getCourseByDuration(duration)) {
            studentDTOList.add(toDTO(course));
        }
        return studentDTOList;
    }
    public List<CourseDTO> getCourseByPrice(Double price) {
        List<CourseDTO> studentDTOList = new LinkedList<>();
        for (CourseEntity course : courseRepository.getCourseByPrice(price)) {
            studentDTOList.add(toDTO(course));
        }
        return studentDTOList;
    }

    public List<CourseDTO> getCourseByPriceBetween(Double priceStart, Double priceEnd) {
        List<CourseDTO> courseDTOS = new LinkedList<>();
        for (CourseEntity course :  courseRepository.getCourseByPriceBetween(priceStart, priceEnd)) {
            courseDTOS.add(toDTO(course));
        }
        return courseDTOS;
    }

    public List<CourseDTO> getCourseByCreatedDateBetween(String start, String end) {
        List<CourseDTO> courseDTOS = new LinkedList<>();
        for (CourseEntity course : courseRepository.getCourseByCreatedDateBetween(
                LocalDateTime.parse(start)
                ,LocalDateTime.parse(end))) {
            courseDTOS.add(toDTO(course));
        }
        return courseDTOS;
    }



    private CourseDTO toDTO(CourseEntity courseEntity) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(courseEntity.getName());
        courseDTO.setPrice(courseEntity.getPrice());
        courseDTO.setDuration(courseEntity.getDuration());
        return courseDTO;
    }
}
