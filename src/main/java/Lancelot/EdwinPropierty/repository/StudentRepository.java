package Lancelot.EdwinPropierty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Lancelot.EdwinPropierty.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
