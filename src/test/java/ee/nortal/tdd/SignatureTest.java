package ee.nortal.tdd;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static ee.nortal.tdd.TestDatabase.addUser;
import static ee.nortal.tdd.TestUtils.readFileBody;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignatureTest {

  @TestConfiguration
  static class Config {

    @Bean
    @Primary
    public DataSource dataSource() {
      return TestDatabase.setUp();
    }
  }

  @LocalServerPort
  int port;

  @Test
  public void createSignature() {
    addUser("John Matrix", "301020304050601");
    given()
      .port(port)
      .contentType(ContentType.JSON)
      .body(readFileBody("valid-signature-request.json"))
      .log().everything()
    .when()
      .post("/signature/create")
    .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("userFullName", equalTo("John Matrix"))
      .body("signature", equalTo("48656C6C6F20576F726C64"))
      .log().everything();
  }

  @Test
  public void createSignature_whenUserNotFound_shouldReturnErrorCode() {
    given()
      .port(port)
      .contentType(ContentType.JSON)
      .body(readFileBody("invalid-user-signature-request.json"))
      .log().everything()
    .when()
      .post("/signature/create")
    .then()
      .statusCode(400)
      .contentType(ContentType.JSON)
      .body("errorCode", equalTo("USER_NOT_FOUND"))
      .log().everything();

  }
}
