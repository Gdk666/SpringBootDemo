package com.mongodb.mongodemo.Controller;

import java.util.List;
import java.util.Map;

import com.mongodb.mongodemo.Component.MyProperties1;
import com.mongodb.mongodemo.Service.UserService;
import com.mongodb.mongodemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * user控制器
 * 
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MyProperties1 myProperties1;


    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView modelAndView){
        List<User> list = userService.findAll();
        modelAndView.addObject("lists",list);
        modelAndView.setViewName("hello");
        return modelAndView;
    }
    @GetMapping("/1")
    public MyProperties1 myProperties1() {
        log.info("=================================================================================================");
        log.info(myProperties1.toString());
        log.info("=================================================================================================");
        return myProperties1;
    }


    @GetMapping("/get/{id}")
    public ModelAndView getUser(ModelAndView modelAndView,@PathVariable int id) {
        User user = userService.getUser(id);
        modelAndView.addObject("author",user);
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        userService.remove(id);
        return "delete success";
    }

    @RequestMapping("/add")
    public String insert() {
        User user =new User(16, ""+16, 16);
        userService.insert(user);
        return "success";
    }

    /*@RequestMapping("/insert")
    public String insertAll() {
        List<User> list = new ArrayList<>();
        for (int i = 10; i < 15; i++) {
            list.add(new User(i, "" + i, i));
        }
        userService.insertAll(list);
        return "sucess";
    }*/

    @RequestMapping("/find/all")
    public List<User> find(){
        return userService.findAll();
    }

    @RequestMapping("/find/{start}")
    public List<User> findByPage(@PathVariable int start,User user){
        Pageable pageable=new PageRequest(start, 2);
        return userService.findByPage(user, pageable);
    } 

    /*@RequestMapping("/update/{id}")
    public String update(@PathVariable int id){
        User user =new User(id, ""+1, 1);
        userService.update(user);
        return "sucess";
    }*/
}