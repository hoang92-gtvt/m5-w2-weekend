package com.module.case4.model.course;

import com.module.case4.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

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
    @Enumerated(EnumType.STRING)
//    @NaturalId
    private Status status = Status.WAIT;

    @ManyToOne
    @JoinColumn(name = "userTeacher_id")
    private User userTeacher;

    @ManyToOne
    @JoinColumn(name = "userStudent_id")
    private User userStudent;

}
