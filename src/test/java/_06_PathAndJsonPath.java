import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class _06_PathAndJsonPath {

    @Test
    public void extractingPath()
    {
        //Api çalıştırıldığında veriyi dışarı almanın 2 yöntemini gördük
        //1- extract.path("country")    2- extract.body.as(ToDo.class)

        String postCode=
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().path("'post code'")   // "post code": "90210",
                ;

        System.out.println("postCode = " + postCode);
        int postCodeInt= Integer.parseInt(postCode);
        System.out.println("postCodeInt = " + postCodeInt);
    }

    @Test
    public void extractingPath2()
    {
        //Api çalıştırıldığında veriyi dışarı almanın 2 yöntemini gördük
        //1- extract.path("country")    2- extract.body.as(ToDo.class)

        int postCode=
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().path("'post code'")   // "post code": "90210",
                ;

        System.out.println("postCode = " + postCode);
    }

    @Test
    public void extractingJsonPath()
    {
        int postCode=
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().jsonPath().getInt("'post code'")   // "post code": "90210",
                ; // 1.Avantaj : tip dönüşümü otomatik, uygun tip verilmeli

        System.out.println("postCode = " + postCode);
    }
    
}
