package Lancelot.EdwinPropierty.model;

import java.util.List;

public class HistorialDTO {
    private String nameStudent;
    private List<AcademicRegister> registros;

    public HistorialDTO(String nameStudent, List<AcademicRegister> registros) {
        this.nameStudent = nameStudent;
        this.registros = registros;
    }
    public List<AcademicRegister> getRegistros() {
        return registros;
    }
    public void setRegistros(List<AcademicRegister> registros) {
        this.registros = registros;
    }
    public String getNameStudent() {
        return nameStudent;
    }
    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }
}
