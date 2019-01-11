package Guoz.Service;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import Guoz.service.rabbitService.RabbitService;

/**
 * @description:
 * @Data 15:22
 * @Version 1.0
 * @author: Guoz
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//提供虚拟环境随机端口
public class ActivitiTest {
    private static Logger logger = LoggerFactory.getLogger(ActivitiTest.class);
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;


    @Test
    public void test(){
        Deployment deploy = repositoryService.createDeployment()
            .addClasspathResource("BPMN/testApprove.bpmn")
            .addClasspathResource("static/testApprove.bpmn.png")
            .deploy();
        System.out.println(deploy.toString());

    }

    @Test
    public void testStartProcessInstance(){
        ProcessEngine p = ProcessEngines.getDefaultProcessEngine();
        p.getRuntimeService().startProcessInstanceById("myProcess:1:4");
        System.out.println(p.getRuntimeService().startProcessInstanceById("myProcess:1:4").toString());

    }

    @Test
    public void queryAllDeplyoment(){
        //得到流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<Deployment> lists = processEngine.getRepositoryService()
            .createDeploymentQuery()
            .orderByDeploymenTime()//按照部署时间排序
            .desc()//按照降序排序
            .list();
        for (Deployment deployment:lists) {
            System.out.println(deployment.getId() +"    部署名称" + deployment.getName());
        }
    }





}
