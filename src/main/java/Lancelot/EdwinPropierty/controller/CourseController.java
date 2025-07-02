package Lancelot.EdwinPropierty.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Lancelot.EdwinPropierty.model.Course;
import Lancelot.EdwinPropierty.services.CourseService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseByID(@PathVariable Long id){
        return courseService.findById(id);
    }

    @PostMapping
    public Course saveCourse(@RequestBody Course course){
        return courseService.save(course);
    }

    @PutMapping("/{idCourse}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long idCourse, @RequestBody Course course){
        Course updateCourse = courseService.update(idCourse, course);
        return ResponseEntity.ok(updateCourse);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.delete(id);
    }
}
