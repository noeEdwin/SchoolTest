package Lancelot.EdwinPropierty.model;

import java.time.LocalTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_students")
public class CourseStudent {
    @EmbeddedId
    private CourseStudentId id;

    @ManyToOne
    @MapsId("studentId") // mapea con CourseStudentId.studentId
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @MapsId("courseId") // mapea con CourseStudentId.courseId
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private LocalTime startTime;

    private LocalTime endTime;

    public CourseStudent() {}

    public CourseStudent(Student student, Course course, LocalTime startTime, LocalTime endTime) {
        this.id = new CourseStudentId(student.getId(), course.getId());
        this.student = student;
        this.course = course;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public CourseStudentId getId() {
        return id;
    }

    public void setId(CourseStudentId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @PrePersist
    @PreUpdate
    private void validateTimeRange() {
        if (endTime != null && startTime != null && !endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("La hora de fin debe ser posterior a la de inicio");
        }
    }
}
