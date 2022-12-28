package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.log4j.Log4j2;
import util.TestContext;


@Log4j2
public class Hook  {

    @Before
    public void beforeScenario() {
        log.info("Teste iniciado");
    }

    @After
    public void afterScenario() {
        log.info("Teste Finalizado");
        TestContext.CONTEXT.reset();
    }

}
