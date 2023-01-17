package database;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.Arrays;

public class Database {

    @SneakyThrows
    public Connection getConnectionDataBase(){
        String url = "jdbc:postgresql://"+System.getenv("DB_HOST")+"/pizza-db?schema=public";
        Connection connection = DriverManager.getConnection(url, "postgres", "pizza-postgres");
        connection.createStatement();
        return connection;
    }

    public void createUserDefault(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into public.user (id, name, email, password)\n" +
                "values ('1','testUser','testUser@mail.com','$2a$08$3/dRTPZNkxbuO7JXZYiTHu0ybAmaODyEPtnLe6TWVamC7uLKMqbAa');");
        preparedStatement.execute();
    }

    public void clearData(Connection connection) {
        String[] tables = {"user", "category"};
        Arrays.stream(tables).forEach(table -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE public."+table+" CASCADE;");
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
