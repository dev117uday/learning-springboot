package com.example.jpa.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseMaterialId;

    private String materialUrl;

    @OneToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private Course course;

}
