package Guoz.Service;


import Guoz.service.ManagerService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//提供虚拟环境随机端口
public class UserServiceTest {
    private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    @Autowired
    private ManagerService managerService;

    @Rule
    public ActivitiRule activitiRule= new ActivitiRule();

    @Test
    @Deployment(resources = {"BPMN/second_approve.bpmn20.xml"})
    public void name() throws Exception {
        logger.info("start");
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("second_approve");



    }



}