package com.module.case4.model.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String keyName;

    @NotBlank
    @ManyToOne
    @JoinColumn( name = "subject")
    private Subject subject;

    @NotBlank
    @Min(6)
    @Max(12)
    private Long groupName;



    @ManyToOne
    @JoinColumn( name = "district")
    private District district;

    @NotBlank
    private String periodOfTime;

    private String description;

}
