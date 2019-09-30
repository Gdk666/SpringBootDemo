package Guoz.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
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

    //仓库流程service，用于管理流程仓库，例如部署丶删除丶读取流程资源
    @Autowired
    private RepositoryService repositoryService;

    //运行时Service，可以处理所有正在运行状态的流程实例，任务等
    @Autowired
    private RuntimeService runtimeService;

    //任务Service,用于管理丶查询任务，例如：签收丶办理丶指派等
    @Autowired
    private TaskService taskService;

    //表单Service,读取和流程中的表单数据
    /*@Autowired
    private FormService formService;*/

    //历史Service，查询历史数据
    /*@Autowired
    private HistoryService historyService;*/

    //引擎管理Service,查询引擎配置丶数据库
    /*@Autowired
    private ManagementService managementService;*/

    /*@Rule*/
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    public void test() throws FileNotFoundException {
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("BPMN/second_approve.bpmn20.xml").deploy();
        System.out.println(deploy.toString());

    }

    @Test
    public void testStartProcessInstance() {
        ProcessEngine p = ProcessEngines.getDefaultProcessEngine();
        p.getRuntimeService().startProcessInstanceById("process-1:1:37504");
        System.out.println(p.getRuntimeService().startProcessInstanceById("process-1:1:37504").toString());

    }

    @Test
    public void queryAllDeplyoment() {
        //得到流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<Deployment> lists = processEngine.getRepositoryService().createDeploymentQuery()
            .orderByDeploymenTime()//按照部署时间排序
            .desc()//按照降序排序
            .list();
        for (Deployment deployment : lists) {
            System.out.println(deployment.getId() + "    部署名称" + deployment.getName());
        }
    }

    @Test
    public void startProcess() throws FileNotFoundException {
        //RepositoryService repositoryService = repositoryService.getRepositoryService();
        //部署流程文件
        repositoryService.createDeployment().addClasspathResource("BPMN/second_approve.bpmn20.xml")
            .addClasspathResource("BPMN/second_approve.bpmn20.png")
            .deploy();
        //运行
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> m = new HashMap<>();
        m.put("name", "二级审批");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("secondapprove", m);
        System.out.println(processInstance.getName() + " " + processInstance.getId());
    }



}
