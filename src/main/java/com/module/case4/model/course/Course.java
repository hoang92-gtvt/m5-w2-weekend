package com.module.case4.model.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String key;

    @NotBlank
    @OneToOne
    private Subject subject;

    @NotBlank
    @Min(6)
    @Max(12)
    private Long group;


    @NotBlank

    @ManyToOne
    private District district;

    @NotBlank
    private Date time;

    private String description;

}
