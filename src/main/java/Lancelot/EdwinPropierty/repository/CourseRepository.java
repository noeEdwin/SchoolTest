
package Lancelot.EdwinPropierty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Lancelot.EdwinPropierty.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
