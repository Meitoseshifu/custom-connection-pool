package ua.happy.learning;

import lombok.SneakyThrows;
import ua.happy.learning.datasource.DataSourceImpl;

import javax.sql.DataSource;

public class Demo {
    /*private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";*/

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final int POOL_SIZE = 30;

    private static DataSource dataSource;

    @SneakyThrows
    public static void main(String[] args) {
        dataSource = new DataSourceImpl(URL, USERNAME, PASSWORD, POOL_SIZE);
        long start = System.nanoTime();
        for (int i = 1; i <= 1000; i++) {
            try(var connection = dataSource.getConnection()) {
                //trali vali festivali
            }
        }
        long end = System.nanoTime();
        System.out.println("It took: " + (end - start) / 1_000_000 + " millis");
    }

}
