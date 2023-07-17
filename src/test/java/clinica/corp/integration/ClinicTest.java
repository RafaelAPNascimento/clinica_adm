package clinica.corp.integration;

import clinica.corp.annotations.IntegrationTest;
import clinica.corp.model.Clinic;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ClinicTest {

    private static final String BASE_URI = "http://localhost:8585/clinic_adm/api/v1";

    @Value("classpath:objects/clinic.json")
    private Resource clinicJson;

    private Clinic clinic;

    @BeforeAll
    public void init() throws IOException {
        clinic = load();
    }

    private Clinic load() throws IOException {
        try (InputStream inputStream = clinicJson.getInputStream()) {
            return new ObjectMapper().readValue(inputStream, Clinic.class);
        }
    }

    @IntegrationTest
    @Order(1)
    public void shouldSaveClinic() {

        given().baseUri(BASE_URI)
                .basePath("/clinic")
                .request()
                .contentType(JSON)
                .body(clinic)
                .log().all()
                .when().post()
                .then().log().all()
                .assertThat().statusCode(SC_CREATED);
    }

    @IntegrationTest
    @Order(2)
    public void shouldUpdateClinic() {

        clinic.setId(2L);
        clinic.setName("Updated");

        given().baseUri(BASE_URI)
                .basePath("/clinic")
                .request()
                .contentType(JSON)
                .body(clinic)
                .log().all()
                .when().put()
                .then().log().all()
                .assertThat().statusCode(SC_OK);
    }

    @IntegrationTest
    @Order(3)
    public void shouldGetAll() {

        given().baseUri(BASE_URI)
                .basePath("/clinic/all")
                .request()
                .log().all()
                .when().get()
                .then().log().all()
                .assertThat().statusCode(SC_OK);
    }
}
