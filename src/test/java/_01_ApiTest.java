import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _01_ApiTest {

    @Test
    public void Test1()
    {
        // 1- Endpoint i çağırmadna önce hazırlıkların yapıldığı bölüm : Request, gidecek body, token
        // 2- Endpoint in çağrıldığı bölüm  : Endpoint in çağrılması(METOD: GET,POST ..)
        // 3- Endpoint çağrıldıktan sonraki bölüm : Response, Test(Assert), data

        given().
                //1.bölümlerle ilgili işler : giden body,token
                when().
                //2.bölümlerle ilgili işler : gidiş metodu , endpoint, apinin çağrılma kısmı
                then()
                //3.bölümlerle ilgili işler: gelen data, assert,test
                ;
    }

    @Test
    public void statusCodeTest()
    {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  // dönüş datasını gösterir  all: bütün bilgiler
                .statusCode(200) // dönen değer 200 e eşitmi, assert
        ;
    }

    @Test
    public void contentTypeTest()
    {
          given()

                  .when()
                  .get("http://api.zippopotam.us/us/90210")

                  .then()
                  .log().body()  // dönen body yi yaz
                  .statusCode(200)  // donen status code 200 MÜ assert
                  .contentType(ContentType.JSON) // donen içerik formatı JSON MI assert
          ;
    }

    @Test
    public void checkCountryInResponseBody()
    {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body() // dönüş datalarını yaz / all: tüm dönüş bilgilerini yaz
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("country", equalTo("United States"))  // assert
                // country yi dışarı almadan
                // bulundu yeri (path i) vererek içerde assertion yapıyorum.
                // Bunu hamcrest kütüphanesi yapıyor
        ;


    }

}









