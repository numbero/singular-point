package org.example.detault.ui.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class UserVO {
    private Long id;
    @NotBlank
    private String name;
    @Min(18)
    @Max(100)
    private Integer age;
}
