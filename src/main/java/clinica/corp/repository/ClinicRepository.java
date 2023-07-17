package clinica.corp.repository;

import clinica.corp.model.Clinic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClinicRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Clinic clinic) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO clinic (name, email, creation_date) ");
        sql.append("values (:name, :email, now()) ");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("name", clinic.getName());
        query.setParameter("email", clinic.getEmail());
        query.executeUpdate();
    }

    public void update(Clinic clinic) {

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE clinic SET name = :name, email = :email ");
        sql.append("WHERE id = :id ");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("name", clinic.getName());
        query.setParameter("email", clinic.getEmail());
        query.setParameter("id", clinic.getId());
        query.executeUpdate();
    }

    public List<Clinic> getAll() {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name, email FROM clinic c");

        Query query = entityManager.createNativeQuery(sql.toString());
        List<Object[]> result = query.getResultList();

        List<Clinic> clinics = new ArrayList<>(result.size());

        result.forEach( obj -> {
            Clinic c = new Clinic();
            c.setId((Long) obj[0]);
            c.setName((String) obj[1]);
            c.setEmail((String) obj[2]);
            clinics.add(c);
        });
        return clinics;
    }
}
