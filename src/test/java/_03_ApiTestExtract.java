import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _03_ApiTestExtract {

    @Test
    public void extractingJsonPath() {

        //var dataUserID=pm.response.json().id;
        String ulke=
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .extract().path("country") // PATH i country olan değeri EXTRACT yap
        ;

        System.out.println("ulke = " + ulke);
        Assert.assertEquals(ulke, "United States");
    }

    @Test
    public void extractingJsonPath2() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
        // place dizisinin ilk elemanının state değerinin  "California"
        // olduğunu testNG Assertion ile doğrulayınız

        String state=
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                //.body("places[0].state", equalTo("California"))
                .extract().path("places[0].state")  // extract en son komut olmalı
        ;

        System.out.println("state = " + state); // kendimize kontrol
        Assert.assertEquals(state,"California"); // assert
    }

    @Test
    public void extractingJsonPath3() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // limit bilgisinin 10 olduğunu testNG ile doğrulayınız.

       int limit=
       given()

               .when()
               .get("https://gorest.co.in/public/v1/users")

               .then()
               //.log().body()
               .extract().path("meta.pagination.limit")
       ;

       Assert.assertTrue(limit == 10);
    }
}










