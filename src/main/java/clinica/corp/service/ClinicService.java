package clinica.corp.service;

import clinica.corp.model.Clinic;
import clinica.corp.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Transactional
    public void save(Clinic clinic) {

        Objects.requireNonNull(clinic, "Clinic is null");

        if (clinic.getId() == null)
            clinicRepository.save(clinic);
        else
            clinicRepository.update(clinic);
    }

    public List<Clinic> getAll() {
        return clinicRepository.getAll();
    }
}
