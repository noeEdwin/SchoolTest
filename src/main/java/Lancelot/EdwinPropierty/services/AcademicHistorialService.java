package Lancelot.EdwinPropierty.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

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

    public AcademicHistorial save(AcademicHistorial historial){
        return historialRepo.save(historial);
    }

    public AcademicRegister update(Long idStudent, AcademicRegister newRegister) {
        if (newRegister.getIdCourse() == null) {
            throw new RuntimeException("idCourse is required for updating a register");
        }
        return historialRepo.findByStudentId(idStudent).map(historial -> {
            AcademicRegister existing = historial.getRegister().stream()
                .filter(r -> r.getIdCourse() != null && r.getIdCourse().equals(newRegister.getIdCourse()))
                .findFirst()
                .orElse(null);

            if (existing != null) {
                if (newRegister.getNota() != null) existing.setNota(newRegister.getNota());
                if (newRegister.getAssistance() != null) existing.setAssistance(newRegister.getAssistance());
                if (newRegister.getComments() != null) existing.setComments(newRegister.getComments());
                if (newRegister.getNameCourse() != null) existing.setNameCourse(newRegister.getNameCourse());
            } else {
                // Only add if idCourse is not null (already checked above)
                historial.getRegister().add(newRegister);
            }

            historialRepo.save(historial);
            return newRegister;
        }).orElseThrow(() -> new RuntimeException("Historial no encontrado para el estudiante con id: " + idStudent));
    }

    @DeleteMapping
    public boolean deleteAcademicRegister(Long idStudent, Long idCourse) {
        Optional<AcademicHistorial> historialOpt = historialRepo.findByStudentId(idStudent);
        Boolean deleted = false;
        if (historialOpt.isPresent()) {
            AcademicHistorial historial = historialOpt.get();
            List<AcademicRegister> registros = historial.getRegister();
            boolean removed = registros.removeIf(r -> Objects.equals(r.getIdCourse(), idCourse));
            if (removed) {
                historialRepo.save(historial);
                deleted = true;
            }
        }
        return deleted;
    }
}
