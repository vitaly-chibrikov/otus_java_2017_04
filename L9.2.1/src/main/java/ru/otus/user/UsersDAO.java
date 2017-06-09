package ru.otus.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Mapper for table users.
 */

public interface UsersDAO {

    /**
     * Black magic for execution of custom query.
     */
    String SQL = "sql";

    @Update("${" + SQL + "}")
    void execute(Map<String, String> m);

    @Insert("insert into users (name) values (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(UsersDataSet dataSet);

    @Select("SELECT * FROM users WHERE name = #{name} limit 1")
    UsersDataSet select(String name);

    @Select("SELECT * FROM users")
    List<UsersDataSet> selectAll();

}
