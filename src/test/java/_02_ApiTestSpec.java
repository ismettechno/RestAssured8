import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _02_ApiTestSpec {
    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

    @BeforeClass
    public void Setup()
    {
        reqSpec=new RequestSpecBuilder()   // istek paketi setlenmesi
                .setContentType(ContentType.JSON)  // giden body cinsi
                .log(LogDetail.URI)  // log.uri
                //token
                .build();

        resSpec=new ResponseSpecBuilder()  // cevap geldikten sonraki yapılacaklar
                .log(LogDetail.BODY)
                .expectContentType(ContentType.JSON)
                .build();
    }


    @Test
    public void Test1()
    {
        given()
                .spec(reqSpec)  // istek paketi

                .when()
                .get("https://gorest.co.in/public/v1/users")

                .then()
                .spec(resSpec)  // dönüş yapılacakları
        ;
    }

    @Test
    public void Test2()
    {
        given()
                .spec(reqSpec) // istek paketi

                .when()
                .get("https://gorest.co.in/public/v1/users")

                .then()
                .spec(resSpec) // dönüş yapılacakları
        ;
    }

}
