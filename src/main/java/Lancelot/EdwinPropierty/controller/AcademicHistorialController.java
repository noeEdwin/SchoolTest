package Lancelot.EdwinPropierty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Lancelot.EdwinPropierty.model.AcademicHistorial;
import Lancelot.EdwinPropierty.model.AcademicRegister;
import Lancelot.EdwinPropierty.model.HistorialDTO;
import Lancelot.EdwinPropierty.model.Student;
import Lancelot.EdwinPropierty.services.AcademicHistorialService;

@RestController
@RequestMapping("/historial")
public class AcademicHistorialController {
    private final AcademicHistorialService historialService;

    public AcademicHistorialController(AcademicHistorialService historialService){
        this.historialService = historialService;
    }

    @PostMapping
    public AcademicHistorial saveHistorial(@RequestBody AcademicHistorial historial){
        return historialService.save(historial);
    }

    @GetMapping("/{idStudent}")
    public ResponseEntity<HistorialDTO> getHistorialByStudent(@PathVariable Long idStudent) {
        return historialService.findByIdStudent(idStudent)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idStudent}")
    public ResponseEntity<HistorialDTO> updateHistorial(
            @PathVariable Long idStudent,
            @RequestBody AcademicRegister newRegister) {

        try {
            // Actualiza o agrega el registro
            historialService.update(idStudent, newRegister);

            // Busca el historial actualizado
            return historialService.findByIdStudent(idStudent)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

        } catch (RuntimeException e) {
            // Si hay un error (por ejemplo, no se encuentra curso/estudiante)
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{idStudent}/{idCourse}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long idStudent, @PathVariable Long idCourse){
        boolean deleted = historialService.deleteAcademicRegister(idStudent, idCourse);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
