package ee.nortal.tdd;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static ee.nortal.tdd.TestUtils.readFileBody;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignatureTest {

  @LocalServerPort
  int port;

  @Test
  public void createSignature() {
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
      .body(equalTo(readFileBody("valid-signature-response.json")))
      .log().everything();
  }
}
