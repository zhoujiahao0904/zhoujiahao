package cn.bdqn.service.impl;

import cn.bdqn.dao.UserMapper;

import cn.bdqn.pojo.PageBen;
import cn.bdqn.pojo.User;
import cn.bdqn.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void update(String username, Integer id) {
        userMapper.update(username,id);
    }

    @Override
    public void add(String username, String password) {
        userMapper.add(username,password);
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public PageBen<User> findlimit(String username, String password, int currPageNo, int pageSize) {
        PageBen<User>pageBean = new PageBen<>();
        pageBean.setCurrPageNo(currPageNo);
        pageBean.setPageSize(pageSize);
        int total = userMapper.findTotal(username,password);
        pageBean.setTotalCount(total);
        int totalPageCount = total%pageSize==0?total/pageSize:total/pageSize+1;
        pageBean.setTotalPageCount(totalPageCount);
        List<User>list = userMapper.findlimit(username,password,(currPageNo-1)*pageSize,pageSize);
        pageBean.setLists(list);
        return pageBean;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
