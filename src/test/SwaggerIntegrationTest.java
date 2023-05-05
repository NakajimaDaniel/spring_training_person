
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.webEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

  @Test
  public void shouldDisplaySawggerUiPage() {

    var content = given()
        .basePath("/swagger-ui-index.html")
        .port(TestConfigs.SERVER_PORT)
        .when()
        .get()
        .then()
        .statusCode(200)
        .extract()
        .body().asString();
  }
}
