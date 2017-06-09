package ru.otus.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import ru.otus.user.UsersDAO;
import ru.otus.user.UsersDataSet;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tully.
 */
public class MainAnnotationsExample {
    public static void main(String[] args) {
        new MainAnnotationsExample().run();
    }

    private void run() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(getMySQLDataSource());
        //SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(getH2DataSource());
        try (SqlSession session = sqlSessionFactory.openSession(false)) {

            UsersDAO dao = session.getMapper(UsersDAO.class);
            createTableIfNotExists(dao);

            dao.save(new UsersDataSet("tully"));
            session.commit();

            UsersDataSet dataSet = dao.select("tully");
            System.out.println(dataSet);
        }
    }

    private void createTableIfNotExists(UsersDAO dao) {
        Map<String, String> createMap = new HashMap<>();
        createMap.put(UsersDAO.SQL, "create table  if not exists users (id bigint auto_increment, name varchar(256), primary key (id))");
        dao.execute(createMap);
    }

    private SqlSessionFactory getSqlSessionFactory(DataSource dataSource) {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        configuration.addMapper(UsersDAO.class);

        return new SqlSessionFactoryBuilder().build(configuration);
    }

    private DataSource getMySQLDataSource() {
        PooledDataSource ds = new PooledDataSource();

        ds.setDriver("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db_example");
        ds.setUsername("tully");
        ds.setPassword("tully");

        return ds;
    }

    private DataSource getH2DataSource() {
        PooledDataSource ds = new PooledDataSource();

        ds.setDriver("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:test");

        return ds;
    }

}
