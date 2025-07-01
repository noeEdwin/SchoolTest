package Lancelot.EdwinPropierty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Invalid name")
    @Size(max = 100)
    @Column(name = "name_course", nullable = false, length = 100)
    private String courseName;

    @NotBlank(message = "Invalid description")
    @Size(max = 300)
    @Column(name = "description", nullable = false, length = 300)
    private String description;

    public Course(){}

    public Course(Long id, @NotBlank(message = "Invalid name") @Size(max = 100) String courseName,
            @NotBlank(message = "Invalid description") @Size(max = 300) String description) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
