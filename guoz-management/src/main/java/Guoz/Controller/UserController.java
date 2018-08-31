package Guoz.Controller;



import Guoz.Service.UserService;
import Guoz.model.User;
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
import java.util.List;


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
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @GetMapping("/hello")
    @ApiOperation(value = "查询所有用户",notes = "查询用户列表")
    public ModelAndView hello(ModelAndView modelAndView){
        List<User> list = userService.findAll();
        modelAndView.addObject("lists",list);
        modelAndView.setViewName("hello");
        return modelAndView;
    }


    @GetMapping("/get/{id}")
    public ModelAndView  getUser(@PathVariable int id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser111((long) id);
        mav.addObject("author",user);
        mav.setViewName("hello");
        return mav;
    }


    @RequestMapping("/find/{start}")
    public List<User> findByPage(@PathVariable int start,User user){
        Pageable pageable=new PageRequest(start, 2);
        return userService.findByPage(user, pageable);
    } 


}