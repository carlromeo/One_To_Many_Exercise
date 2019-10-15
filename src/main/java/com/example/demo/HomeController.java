package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    StudentRepository studentRepository;



    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("classrooms", classroomRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }

    @GetMapping("/addclassroom")
    public String classroomForm(Model model){
            model.addAttribute("classroom", new Classroom());
            return "classroomform";
        }

    @PostMapping("/process_classroom")
    public String processClassroomForm(@Valid Classroom classroom, BindingResult result) {
        if (result.hasErrors()) {
            return "classroomform";
        }
        classroomRepository.save(classroom);
        return "redirect:/classroomlist";
    }

    @RequestMapping("/classroomlist")
    public String classroomList(Model model){
        model.addAttribute("classrooms", classroomRepository.findAll());
        return "classroomlist";
    }

    @GetMapping("/addstudent")
    public String studentForm(Model model){
        model.addAttribute("classrooms", classroomRepository.findAll());
        model.addAttribute("student", new Student());
        return "studentform";
    }

    @PostMapping("/processstudent")
    public String processStudentForm(@Valid Student student, BindingResult result){
        if (result.hasErrors()){
            return "studentform";
        }
        studentRepository.save(student);
        return "redirect:/studentlist";
    }

    @RequestMapping("/studentlist")
    public String studentList(Model model){
        model.addAttribute("students", studentRepository.findAll());
        return "studentlist";
    }

    @RequestMapping("/detail/{id}")
    public String showClassroom(@PathVariable("id") long id, Model model){
        model.addAttribute("classroom", classroomRepository.findById(id).get());
        return "showclassroom";
    }

    @RequestMapping("/update/{id}")
    public String updateClassroom(@PathVariable("id") long id, Model model){
        model.addAttribute("classroom", classroomRepository.findById(id).get());
        return "classroomform";
    }

    @RequestMapping("/delete/{id}")
    public String delClassroom(@PathVariable("id") long id){
        classroomRepository.deleteById(id);
        return "index";
    }

    @RequestMapping("/detailstudent/{id}")
    public String showStudent(@PathVariable("id") long id, Model model){
        model.addAttribute("student", studentRepository.findById(id).get());
        return "showstudent";
    }

    @RequestMapping("/updatestudent/{id}")
    public String updateStudent(@PathVariable("id") long id, Model model){
        model.addAttribute("student", studentRepository.findById(id).get());
        model.addAttribute("classrooms",classroomRepository.findAll());
        return "studentform";
    }

    @RequestMapping("/deletestudent/{id}")
    public String delStudent(@PathVariable("id") long id){
        studentRepository.deleteById(id);
        return "index";
    }

}



