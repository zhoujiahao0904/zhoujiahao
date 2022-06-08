package cn.bdqn.controller;

import cn.bdqn.pojo.PageBen;
import cn.bdqn.pojo.User;
import cn.bdqn.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {


    @Resource
    private UserService userService;

    @RequestMapping("list")
    public String list(Model model){
        List<User> list=userService.list();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "add";
    }
    @RequestMapping("add")
    public String add(String username,String password){
        userService.add(username,password);
        return "redirect:/list";
    }
    @RequestMapping("toUpdate")
    public String list(Model model,int id){
        User user=userService.getUserById(id);
        model.addAttribute("user",user);
        return "update";
    }


    @RequestMapping("update")
    public String update(String username,Integer id){
        userService.update(username,id);
        return "redirect:/list";
    }
    @RequestMapping("delete")
    public String delete( Integer id){
        userService.delete(id);
        return "redirect:/list";
    }


    @RequestMapping(value = "find",method = RequestMethod.GET)
    public String findUser(@RequestParam(value = "username",required = false)String username,
                           @RequestParam(value = "password",required = false)String password,
                           @RequestParam(value = "currPageNo",defaultValue = "1",required = false)int currPageNo, Model model, HttpSession session){
        if (username!=null){
            session.setAttribute("username",username);
        }else {
            username=(String) session.getAttribute("username");
        }

        if (password!=null){
            session.setAttribute("password",password);
        }else {
            password=(String) session.getAttribute("password");
        }

        PageBen<User>pageBean = userService.findlimit(username,password,currPageNo,2);
        model.addAttribute("page",pageBean);
        return "list";
    }
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
