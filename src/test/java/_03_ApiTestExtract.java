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




}
