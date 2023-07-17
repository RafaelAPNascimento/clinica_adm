package clinica.corp.controller;

import clinica.corp.model.Clinic;
import clinica.corp.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @PostMapping
    public ResponseEntity<Clinic> save(@RequestBody Clinic clinic) {
        clinicService.save(clinic);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Clinic> update(@RequestBody Clinic clinic) {
        clinicService.save(clinic);
        return ResponseEntity.status(OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Clinic>> update() {
        return ResponseEntity.ok(clinicService.getAll());
    }
}
