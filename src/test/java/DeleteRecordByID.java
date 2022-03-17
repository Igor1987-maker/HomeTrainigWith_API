import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteRecordByID {

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imlnb3JAbWFpbC5ydSJ9.s7bUIfjB-42LtC0HlSuTE72AR1Ph85ARwvDdYYQF_NE";
//20526 20527
    @BeforeMethod

    public void preCondition() {
        RestAssured.baseURI = "https://super-scheduler-app.herokuapp.com/";
        RestAssured.basePath = "api";
    }

    @Test
    public void deleteRecordsSucces(){

      /*  String status = given().header("Authorization",token)
                .when()
                .delete("record/{id}",20526)
                .then()
                .statusCode(200)
                .extract().path("status");

        System.out.println(status);*/

        given().header("Authorization",token)
                .when()
                .delete("record/{id}",20526)
                .then()
                .statusCode(200)
                .assertThat()
                .body("status", containsString("was deleted"));
               //  .body(" ",equalTo(""));

    }
}
