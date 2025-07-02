package Lancelot.EdwinPropierty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lancelot.EdwinPropierty.model.CourseStudent;
import Lancelot.EdwinPropierty.model.CourseStudentId;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, CourseStudentId>{

}
