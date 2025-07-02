package Lancelot.EdwinPropierty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Invalid name")
    @Size(max = 100)
    @Column(name = "name_student", nullable = false, length = 100)
    private String nameStudent;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100)
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    private Integer age;

    @Email(message = "Invalid email")
    @Size(max = 150)
    @Column(unique = true, length = 150)
    private String email;

    public Student() {}

    public Student(Long id, @NotBlank(message = "Invalid name") @Size(max = 100) String nameStudent,
            @NotBlank(message = "El apellido es obligatorio") @Size(max = 100) String lastName,
            @Min(value = 0, message = "La edad debe ser mayor o igual a 0") Integer age,
            @Email(message = "Invalid email") @Size(max = 150) String email) {
        this.id = id;
        this.nameStudent = nameStudent;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNameStudent() {
        return nameStudent;
    }
    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
