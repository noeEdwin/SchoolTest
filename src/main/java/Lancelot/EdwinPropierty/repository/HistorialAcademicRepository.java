package Lancelot.EdwinPropierty.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import Lancelot.EdwinPropierty.model.AcademicHistorial;

@Repository
public interface HistorialAcademicRepository extends MongoRepository<AcademicHistorial, String> {
    Optional<AcademicHistorial> findByStudentId(Long studentId);
}
