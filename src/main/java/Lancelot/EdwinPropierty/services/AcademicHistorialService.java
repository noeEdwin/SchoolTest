package Lancelot.EdwinPropierty.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Lancelot.EdwinPropierty.model.AcademicHistorial;
import Lancelot.EdwinPropierty.model.AcademicRegister;
import Lancelot.EdwinPropierty.model.Course;
import Lancelot.EdwinPropierty.model.HistorialDTO;
import Lancelot.EdwinPropierty.model.Student;
import Lancelot.EdwinPropierty.repository.CourseRepository;
import Lancelot.EdwinPropierty.repository.HistorialAcademicRepository;
import Lancelot.EdwinPropierty.repository.StudentRepository;

@Service
public class AcademicHistorialService {
    private final HistorialAcademicRepository historialRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public AcademicHistorialService(HistorialAcademicRepository historialRepo, StudentRepository studentRepo,
            CourseRepository courseRepo) {
        this.historialRepo = historialRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public HistorialDTO findById(String historialID){
        AcademicHistorial historial = historialRepo.findById(historialID).orElseThrow(() -> new RuntimeException("Not found"));
        Student student = studentRepo.findById(historial.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        List<AcademicRegister> details = historial.getRegister().stream().map(registro ->{
            Course course = courseRepo.findById(registro.getIdCourse()).orElseThrow(() -> new RuntimeException("Course not found"));
            return new AcademicRegister(course.getId(), registro.getNota(), registro.getAssistance(), registro.getComments(), registro.getNameCourse());
        }).toList();

        return new HistorialDTO(student.getNameStudent(), details);
    }

    public Optional<HistorialDTO> findByIdStudent(Long idStudent) {
        Optional<Student>  student = studentRepo.findById(idStudent);

        Optional<AcademicHistorial> optHistorial = historialRepo.findByStudentId(idStudent);

        if (optHistorial.isEmpty()) {
            return Optional.empty();
        }

        AcademicHistorial historial = optHistorial.get();

        List<AcademicRegister> details = historial.getRegister().stream().map(registro -> {
            Course course = courseRepo.findById(registro.getIdCourse())
                .orElseThrow(() -> new RuntimeException("Course not found"));

            return new AcademicRegister(
                course.getId(),
                registro.getNota(),
                registro.getAssistance(),
                registro.getComments(),
                course.getCourseName()
            );
        }).toList();

        return Optional.of(new HistorialDTO(student.get().getNameStudent(), details));
    }
}
