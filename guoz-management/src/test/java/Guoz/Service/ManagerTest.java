package Guoz.Service;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import Guoz.config.rules.Bo.selectProcessorBo;
import Guoz.config.rules.context.RuleContext;
import Guoz.config.rules.context.context;
import Guoz.config.rules.impl.selectStrategy;
import Guoz.controller.BaseController;
import Guoz.mapper.ManagerMapper;
import Guoz.pojo.dto.ManagerDto;
import Guoz.pojo.po.Manager;
import Guoz.service.ManagerService;
import Guoz.service.rabbitService.RabbitService;

import org.apache.ibatis.annotations.Results;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//提供虚拟环境随机端口
public class ManagerTest extends BaseController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource
    private RuleContext ruleContext;

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

    @Test
    public void testCursorQuery() throws SQLException {
        Statement statement = dataSource.getConnection().createStatement();

        String sql = "select count(*) total from t_sys_manager";

        ResultSet results = statement.executeQuery(sql);

        Long applyCount = null;
        while (results.next()) {
            //rs.get+数据库中对应的类型+(数据库中对应的列别名)
            applyCount = results.getLong("total");
        }
        System.out.println(applyCount);



    }
    @Test
    public void strategy(){
        selectProcessorBo selectProcessorBo = new selectProcessorBo();
        selectProcessorBo.setRuleCode(1);
        ruleContext.process(selectProcessorBo);
    }

    @Test
    public void ttt(){
        selectStrategy selectStrategy = new selectStrategy(managerService);
        context context11 = new context(selectStrategy);
        selectProcessorBo selectProcessorBo = new selectProcessorBo();
        selectProcessorBo.setRuleCode(1);
        context11.contextIface(selectProcessorBo);
    }

}