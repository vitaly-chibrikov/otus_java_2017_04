package ru.otus.main;

import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by tully.
 */
public class MyPoolConnectionFactory implements ConnectionFactory {
    private final ConnectionFactory factory;
    private final Queue<Connection> pool = new ConcurrentLinkedQueue<>();

    MyPoolConnectionFactory(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public java.sql.Connection get() {
        if (pool.isEmpty()) {
            java.sql.Connection connection = factory.get();
            pool.add(new Connection(connection));
            System.out.println("New connection created");
        }
        return pool.poll();
    }

    @Override
    public void dispose() {
        pool.forEach(connection -> {
            try {
                connection.superClose();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private class Connection extends ConnectionDelegate {
        Connection(java.sql.Connection connection) {
            super(connection);
        }

        public void close() {
            pool.add(this);
            System.out.println("Connection returned to the pool");
        }

        void superClose() throws SQLException {
            super.close();
        }
    }

}
