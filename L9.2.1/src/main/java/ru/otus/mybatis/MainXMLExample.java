package ru.otus.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import ru.otus.user.UsersDataSet;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by tully.
 */
public class MainXMLExample {
    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("ru.otus.mybatis.insert", new UsersDataSet(1,"sully"));
            session.commit();

            UsersDataSet dataSet = session.selectOne("ru.otus.mybatis.select", "sully");
            System.out.println(dataSet);
        }
    }
}
