package Lancelot.EdwinPropierty.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Lancelot.EdwinPropierty.model.Student;
import Lancelot.EdwinPropierty.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student save(Student student){
        return studentRepository.save(student);
    }

    public List<Student> findALl(){
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id){
        return studentRepository.findById(id);
    }

    public void delete(Long id){
        studentRepository.deleteById(id);
    }

    public Student update(Long id, Student newStudentData) {
        return studentRepository.findById(id)
            .map(student -> {
                student.setNameStudent(newStudentData.getNameStudent());
                student.setLastName(newStudentData.getLastName());
                student.setAge(newStudentData.getAge());
                student.setEmail(newStudentData.getEmail());
                return studentRepository.save(student);
            })
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));
    }
}
