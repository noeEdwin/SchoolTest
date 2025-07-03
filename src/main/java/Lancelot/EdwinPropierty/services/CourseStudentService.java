package Lancelot.EdwinPropierty.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Lancelot.EdwinPropierty.model.CourseStudent;
import Lancelot.EdwinPropierty.model.CourseStudentId;
import Lancelot.EdwinPropierty.repository.CourseStudentRepository;

@Service
public class CourseStudentService {
    private final CourseStudentRepository courseStudentRepository;

    public CourseStudentService(CourseStudentRepository courseStudentRepository){
        this.courseStudentRepository = courseStudentRepository;
    }

    public CourseStudent save(CourseStudent courseStudent){
        if (courseStudent.getStudent() == null || courseStudent.getCourse() == null) {
            throw new IllegalArgumentException("Course and Student must not be null");
        }

        Long studentId = courseStudent.getStudent().getId();
        Long courseId = courseStudent.getCourse().getId();

        CourseStudentId id = new CourseStudentId(studentId, courseId);
        courseStudent.setId(id);

        return courseStudentRepository.save(courseStudent);
    }

    public List<CourseStudent> findAll(){
        return courseStudentRepository.findAll();
    }

    public Optional<CourseStudent> findById(CourseStudentId id){
        return courseStudentRepository.findById(id);
    }

    public void delete(CourseStudentId id){
        courseStudentRepository.deleteById(id);
    }

}
