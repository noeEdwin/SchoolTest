package Lancelot.EdwinPropierty.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Lancelot.EdwinPropierty.model.AcademicHistorial;
import Lancelot.EdwinPropierty.model.AcademicRegister;
import Lancelot.EdwinPropierty.model.Course;
import Lancelot.EdwinPropierty.model.HistorialDTO;
import Lancelot.EdwinPropierty.model.Student;
import Lancelot.EdwinPropierty.repository.CourseRepository;
import Lancelot.EdwinPropierty.repository.HistorialAcademicRepository;
import Lancelot.EdwinPropierty.repository.StudentRepository;
import Lancelot.EdwinPropierty.services.AcademicHistorialService;
import Lancelot.EdwinPropierty.services.CourseService;
import Lancelot.EdwinPropierty.services.StudentService;

@RestController
@RequestMapping("/historial")
public class AcademicHistorialController {
    private final AcademicHistorialService historialService;
    private final StudentService studentService;
    private final CourseService courseService;

    public AcademicHistorialController(AcademicHistorialService historialService, StudentService studentService, CourseService courseService){
        this.historialService = historialService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<HistorialDTO> getHistorialByStudent(@PathVariable Long studentId) {
        return historialService.findByIdStudent(studentId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
