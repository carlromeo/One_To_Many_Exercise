package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    StudentRepository studentRepository;
}