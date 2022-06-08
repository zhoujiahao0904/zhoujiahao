package cn.bdqn.dao;

import cn.bdqn.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserMapper {

    //@Select("select * from User")
    List<User> list();

    //@Insert("insert into User (username,password) values (#{username},#{password})")
    void add(@Param("username") String username,@Param("password") String password);

   // @Update("update User set username=#{username} where id=#{id}")
    void update(@Param("username") String username,@Param("id") Integer id);
    //@Delete("delete from User where id=#{id}")
    void delete(Integer id);
    //@Select("select * from User where id=#{id}")
    User getUserById(@Param("id") int id);

    // @Select("select Count(*) from User")
    int findTotal(@Param("username") String username, @Param("password") String password);
    //@Select("select * from User limit #{currPageNo},#{pageSize}")
    List<User> findlimit(@Param("username") String username,@Param("password") String password,@Param("currPageNo") int currPageNo, @Param("pageSize") int pageSize);
}
