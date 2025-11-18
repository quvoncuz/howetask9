package dasturlash.uz.repository;

import dasturlash.uz.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Long> {
    List<CourseEntity> getCourseByName(String name);
    List<CourseEntity> getCourseByDuration(Integer duration);
    List<CourseEntity> getCourseByPrice(Double price);
    List<CourseEntity> getCourseByPriceBetween(Double priceStart, Double priceEnd);
    List<CourseEntity> getCourseByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
