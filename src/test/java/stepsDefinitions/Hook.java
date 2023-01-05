package stepsDefinitions;

import database.Database;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import util.TestContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Hook  {

    private static final Database database = new Database();

    private static final Connection connection = database.getConnectionDataBase();

    @BeforeAll
    public static void setup() throws SQLException {
        database.createUserDefault(connection);
    }

    @After
    public void afterScenario() {
        TestContext.CONTEXT.reset();
    }

    @AfterAll
    public static void afterAll() {
        database.clearData(connection);
    }

}
