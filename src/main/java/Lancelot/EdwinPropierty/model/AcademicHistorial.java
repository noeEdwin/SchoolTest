package Lancelot.EdwinPropierty.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "academicHistorial")
public class AcademicHistorial {
    @Id
    private String id;

    private Long studentId;

    private List<AcademicRegister> register;

    public AcademicHistorial(String id, Long studentId, List<AcademicRegister> register) {
        this.id = id;
        this.studentId = studentId;
        this.register = register;
    }

    public AcademicHistorial() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<AcademicRegister> getRegister() {
        return register;
    }

    public void setRegister(List<AcademicRegister> register) {
        this.register = register;
    }
}
