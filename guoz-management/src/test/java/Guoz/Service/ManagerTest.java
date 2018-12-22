package Guoz.Service;


import java.util.ArrayList;
import java.util.List;

import Guoz.controller.BaseController;
import Guoz.mapper.ManagerMapper;
import Guoz.pojo.dto.ManagerDto;
import Guoz.pojo.po.Manager;
import Guoz.service.ManagerService;
import Guoz.service.rabbitService.RabbitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//提供虚拟环境随机端口
public class ManagerTest extends BaseController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private ManagerMapper managerMapper;

    @Test
    public void test(){
        ManagerDto d = managerService.getById("1");
        System.out.println(d.toString());
    }

    @Test
    public void updateBch(){
        List<Manager> list = new ArrayList<>();
        Manager manager = new Manager(1,"1");
        Manager manager1 = new Manager(2,"2");
        list.add(manager);
        list.add(manager1);
        managerService.updateBch(list);
        System.out.println("");
    }

}