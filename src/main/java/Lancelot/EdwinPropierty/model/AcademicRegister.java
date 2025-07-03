package Lancelot.EdwinPropierty.model;

public class AcademicRegister {
    private Long idCourse;
    private Double nota;
    private Integer assistance;
    private String comments;
    private String nameCourse;

    public AcademicRegister(Long idCourse, Double nota, Integer assistance, String comments, String nameCourse) {
        this.idCourse = idCourse;
        this.nota = nota;
        this.assistance = assistance;
        this.comments = comments;
        this.nameCourse = nameCourse;
    }

    public AcademicRegister() {
    }

    public Long getIdCourse() {
        return idCourse;
    }
    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }
    public Double getNota() {
        return nota;
    }
    public void setNota(Double nota) {
        this.nota = nota;
    }
    public Integer getAssistance() {
        return assistance;
    }
    public void setAssistance(Integer assistance) {
        this.assistance = assistance;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getNameCourse() {
        return nameCourse;
    }
    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }
}
