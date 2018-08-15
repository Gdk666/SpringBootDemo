package com.Guoz.Controller;

import java.util.List;

import com.Guoz.Component.MyProperties1;
import com.Guoz.utils.Exception.*;
import com.Guoz.Service.UserService;
import com.Guoz.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * user控制器
 * 
 *
    @Api： 描述Controller
    @ApiIgnore： 忽略该Controller，指不对当前类做扫描
    @ApiOperation： 描述Controller类中的method接口
    @ApiParam： 单个参数描述，与@ApiImplicitParam不同的是，他是写在参数左侧的。如（@ApiParam(name = "username",value = "用户名") String username）
    @ApiModel： 描述POJO对象
    @ApiProperty： 描述POJO对象中的属性值
    @ApiImplicitParam： 描述单个入参信息
    @ApiImplicitParams： 描述多个入参信息
    @ApiResponse： 描述单个出参信息
    @ApiResponses： 描述多个出参信息
    @ApiError： 接口错误所返回的信息
 */
@RestController
@RequestMapping("/user")
@Api("user相关Api")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MyProperties1 myProperties1;


    @GetMapping("/hello")
    @ApiOperation(value = "查询所有用户",notes = "查询用户列表")
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
    public ModelAndView  getUser(@PathVariable int id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(id);
        mav.addObject("author",user);
        mav.setViewName("hello");
        return mav;
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