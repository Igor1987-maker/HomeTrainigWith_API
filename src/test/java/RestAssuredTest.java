import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import schedulerdto.*;

import static com.jayway.restassured.RestAssured.given;

public class RestAssuredTest {

    public static String token;

    @BeforeMethod

    public void preCondition() {
        RestAssured.baseURI = "https://super-scheduler-app.herokuapp.com/";
        RestAssured.basePath = "api";
    }

    @Test
    public void loginSuccessTest() {
        AuthRequestDTO auth = AuthRequestDTO.builder()
                .email("igor@mail.ru")
                .password("WwAbb3'$")
                .build();

        ResponseAuthDTO respoonseDto = given()
                .contentType("application/json")
                .body(auth)
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseAuthDTO.class);

        System.out.println(respoonseDto.getStatus());
        System.out.println(respoonseDto.getToken());
        System.out.println(respoonseDto.isRegistration());
        token = respoonseDto.getToken();
    }

   /* @Test
    public void print(){
        System.out.println("********************************"+token);
    }*/

    @Test(dependsOnMethods = "loginSuccessTest")
    public void gettAllRecordsRestAssured() {
        BodyDto bodydto = BodyDto.builder()
                .monthFrom(1)
                .monthTo(12)
                .yearFrom(2022)
                .yearTo(2022)
                .build();

        GetAllRecordsDto allRecordsDto = given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(bodydto)
                .when()
                .post("records")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(GetAllRecordsDto.class);

        for (RecordDto record : allRecordsDto.getRecords()) {
            System.out.println(record.getId());
            System.out.println("******************");
        }
    }
}
