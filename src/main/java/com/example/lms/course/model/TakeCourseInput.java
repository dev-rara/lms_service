package com.example.lms.course.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TakeCourseInput{

    long courseId;
    String userId;
}
