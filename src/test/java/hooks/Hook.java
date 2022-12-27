package hooks;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class Hook  {

    @Before
    public void beforeScenario() {
        log.info("Teste iniciado");
        RestAssured.reset();
        RestAssured.baseURI="http://localhost";
        RestAssured.port = 3333;
    }

}
