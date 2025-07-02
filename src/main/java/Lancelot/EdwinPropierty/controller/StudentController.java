package Lancelot.EdwinPropierty.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Lancelot.EdwinPropierty.model.Student;
import Lancelot.EdwinPropierty.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.findALl();
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student){
        return studentService.save(student);
    }

    @DeleteMapping("/{id_student}")
    public void deleteStudent(@PathVariable Long id_student){
        studentService.delete(id_student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updated = studentService.update(id, student);
        return ResponseEntity.ok(updated);
    }
}
