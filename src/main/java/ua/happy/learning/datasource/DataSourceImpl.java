package ua.happy.learning.datasource;

//import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.SneakyThrows;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DataSourceImpl extends PGSimpleDataSource {
    private Queue<Connection> connectionPool;
    private int poolSize;

    @SneakyThrows
    public DataSourceImpl(String url, String username, String password, int poolSize) {
        super();
        this.poolSize = poolSize;
        setURL(url);
        setUser(username);
        setPassword(password);
        initConnections();
    }

    private void initConnections() throws SQLException {
        connectionPool = new ConcurrentLinkedDeque<>();
        for(int i = 0; i < poolSize; i++) {
            Connection physicalConnection = super.getConnection();
            ConnectionProxy connection = new ConnectionProxy(physicalConnection, connectionPool);
            connectionPool.add(connection);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionPool.poll();
    }
}
