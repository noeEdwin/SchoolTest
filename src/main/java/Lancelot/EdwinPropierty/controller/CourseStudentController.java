package Lancelot.EdwinPropierty.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Lancelot.EdwinPropierty.model.CourseStudent;
import Lancelot.EdwinPropierty.model.CourseStudentId;
import Lancelot.EdwinPropierty.services.CourseStudentService;

@RequestMapping("/inscriptions")
@RestController
public class CourseStudentController {
    private final CourseStudentService courseStudentService;

    public CourseStudentController(CourseStudentService courseStudentService){
        this.courseStudentService = courseStudentService;
    }

    @GetMapping
    public List<CourseStudent> getInscriptions(){
        return courseStudentService.findAll();
    }

    @GetMapping("/search")
    public ResponseEntity<CourseStudent> getInscription(
            @RequestParam Long studentId,
            @RequestParam Long courseId) {

        CourseStudentId id = new CourseStudentId(studentId, courseId);
        return courseStudentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
    }


    @PostMapping
    public CourseStudent saveInscription(@RequestBody CourseStudent courseStudent){
        return courseStudentService.save(courseStudent);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteInscription(@RequestParam Long studentId, @RequestParam Long courseId){
         CourseStudentId id = new CourseStudentId(studentId, courseId);
         courseStudentService.delete(id);
         return ResponseEntity.noContent().build();
    }
}
