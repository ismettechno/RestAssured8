package Campus;

import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class _09_CountryTest {
    Faker randomUreteci = new Faker();
    RequestSpecification reqSpec;
    String CountryID = "";


    @BeforeClass
    public void Setup() {
        // login ol , token al, spec i hazırla
        Map<String, String> credential = new HashMap<>();
        credential.put("username", "Campus25");
        credential.put("password", "Campus.2524");
        credential.put("rememberMe", "true");

        String token =
                given()
                        .contentType(ContentType.JSON)
                        .body(credential)

                        .when()
                        .post("https://test.mersys.io/auth/login")

                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().path("access_token");

        System.out.println("token = " + token);

        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    @Test
    public void CreateCountry() {
        String ulkeAdi = randomUreteci.address().country() + randomUreteci.number().digits(5);
        String ulkeKodu = randomUreteci.address().countryCode() + randomUreteci.number().digits(5);

        Map<String, String> newCountry = new HashMap<>();
        newCountry.put("name", ulkeAdi);
        newCountry.put("code", ulkeKodu);

        CountryID=
        given()
                .spec(reqSpec)
                .body(newCountry)

                .when()
                .post("https://test.mersys.io/school-service/api/countries")

                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id")
        ;

        System.out.println("CountryID = " + CountryID);
    }

    @Test(dependsOnMethods = "CreateCountry")
    public void UpdateCountry()
    {
        Map<String, String> uptCountry = new HashMap<>();
        uptCountry.put("id", CountryID);
        uptCountry.put("name", "İsmet Ülkesi"+randomUreteci.number().digits(5));
        uptCountry.put("code", "is2323"+randomUreteci.number().digits(5));


        given()
                .spec(reqSpec)
                .body(uptCountry)

                .when()
                .put("https://test.mersys.io/school-service/api/countries")

                .then()
                .log().body()
                .statusCode(200)
        ;
    }





}
