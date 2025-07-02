package Lancelot.EdwinPropierty.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Lancelot.EdwinPropierty.model.Course;
import Lancelot.EdwinPropierty.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public Course save(Course course){
        return courseRepository.save(course);
    }

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public Optional<Course> findById(Long id){
        return courseRepository.findById(id);
    }

    public void delete(Long id){
        courseRepository.deleteById(id);
    }

    public Course update(Long id, Course newCourseData){
        return courseRepository.findById(id).map(course -> {
            course.setCourseName(newCourseData.getCourseName());
            course.setDescription(newCourseData.getDescription());
            return courseRepository.save(course);
        }).orElseThrow(() -> new RuntimeException("Not found course with id: " + id));
    }
}
