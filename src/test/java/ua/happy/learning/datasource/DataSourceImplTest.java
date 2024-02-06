package ua.happy.learning.datasource;

import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.sql.DataSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataSourceImplTest {

    /*private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";*/

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private DataSource dataSource;

    @Test
    @Order(1)
    void method1() {
        int poolSize = 50;
        dataSource = new DataSourceImpl(URL, USERNAME, PASSWORD, poolSize);
        util();
    }

    @SneakyThrows
    private void util() {
        long start = System.nanoTime();
        for (int i = 1; i <= 10_000; i++) {
            try(var connection = dataSource.getConnection()) {
                //perform great things
            }
        }
        long end = System.nanoTime();
        System.out.println("It took: " + (end - start) / 1_000_000 + " millis");
    }

}
