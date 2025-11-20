package dasturlash.uz.repository;

import dasturlash.uz.entity.CourseEntity;
import dasturlash.uz.mapper.StudentInfoMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Long> {
    List<CourseEntity> getCourseByName(String name);
    List<CourseEntity> getCourseByDuration(Integer duration);
    List<CourseEntity> getCourseByPrice(Double price);
    List<CourseEntity> getCourseByPriceBetween(Double priceStart, Double priceEnd);
    List<CourseEntity> getCourseByCreatedDateBetween(LocalDateTime start, LocalDateTime end);


    @Query("from CourseEntity where price >: price1 and price <: price2")
    List<CourseEntity> getPriceBetween(@Param("price1") Double priceStart,@Param("price2") Double priceEnd);

    @Query("from CourseEntity where price between ?1 and ?2")
    List<CourseEntity> getPriceBetween2(Double priceStart, Double priceEnd);



}
