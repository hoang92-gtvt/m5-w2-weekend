package com.module.case4.model.course;

import com.module.case4.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    private Date startTime;


    private Date endTime;


    private Status status = Status.WAIT;

    @ManyToOne
    private User userTeacher;

    @ManyToOne
    private User userStudent;

}
