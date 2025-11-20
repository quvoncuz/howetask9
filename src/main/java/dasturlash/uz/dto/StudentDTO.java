package dasturlash.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private String name;
    private String surname;
    private Integer age;
    private Integer level;
    private String gender;
}
