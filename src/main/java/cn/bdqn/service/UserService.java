package cn.bdqn.service;

import cn.bdqn.pojo.PageBen;
import cn.bdqn.pojo.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    List<User> list();

    void add(String username, String password);

    void update(String username, Integer id);

    void delete(Integer id);


    User getUserById(int id);


    PageBen<User> findlimit(String username, String password, int currPageNo, int i);
}
