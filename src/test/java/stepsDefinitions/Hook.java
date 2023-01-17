package stepsDefinitions;


import database.Database;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import util.TestContext;

import java.sql.Connection;
import java.sql.SQLException;

public class Hook {

    private static final Database database = new Database();

    private static final Connection connection = database.getConnectionDataBase();

    @BeforeAll
    public static void setup() throws SQLException {
        try{
            database.createUserDefault(connection);
        } catch (Throwable e) {
            throw new RuntimeException("DEU CACA NO SETUP", e);
        }
    }

    @After
    public void afterScenario() {
        try{
            TestContext.CONTEXT.reset();
        } catch (Throwable e) {
            throw new RuntimeException("DEU CACA NO AFTER", e);
        }
    }

    @AfterAll
    public static void afterAll() {
        try{
            database.clearData(connection);
        } catch (Throwable e) {
            throw new RuntimeException("DEU CACA NO FINAL", e);
        }
    }

}
