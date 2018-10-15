package Guoz.Service;

import com.google.common.collect.Maps;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @description:
 * @Data 15:22
 * @Version 1.0
 * @author: Guoz
 **/
public class test {
    private static Logger logger = LoggerFactory.getLogger(test.class);
    public static void main(String[] args) throws ParseException{
        logger.info("start");
        //创建流程引擎
        ProcessEngine processEngine = getProcessEngine();
        //部署流程定义文件
        ProcessDefinition processDefinition = getProcessDefinition(processEngine);
        //启动运行流程
        final ProcessInstance[] processInstance = {getProcessInstance(processEngine, processDefinition)};
        //处理流程任务
        Scanner scanner = new Scanner(System.in);

        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().list();
        logger.info("待处理任务数量：{}",list.size());
        list.stream().forEach(task -> {
            logger.info("待处理任务：{}",task.getName());
            Map<String, Object> variables = getStringObjectMap(processEngine, scanner, task);
            taskService.complete(task.getId(),variables);
            processInstance[0] = processEngine.getRuntimeService()
                    .createProcessInstanceQuery()
                    .processInstanceId(processInstance[0].getId())
                    .singleResult();
        });

        scanner.close();
        //


        logger.info("end");

    }

    private static Map<String, Object> getStringObjectMap(ProcessEngine processEngine, Scanner scanner, Task task) {
        FormService formService = processEngine.getFormService();
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        Map<String,Object> variables = Maps.newHashMap();
        formProperties.stream().forEach(formProperty -> {
            String line = null;
            if (StringFormType.class.isInstance(formProperty.getType())){
                logger.info("请输入：{} ？",formProperty.getName());
                line = scanner.nextLine();
                variables.put(formProperty.getId(),line);
            }else if (DateFormType.class.isInstance(formProperty.getType())){
                logger.info("请输入{} ? 格式（yyyy-MM-dd）",formProperty.getName());
                line = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = dateFormat.parse(line);
                    variables.put(formProperty.getId(),date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else {
                logger.info("类型不支持{}",formProperty.getType());
            }
            logger.info("输入的内容是:{}",line);

        });
        return variables;
    }

    private static ProcessInstance getProcessInstance(ProcessEngine processEngine, ProcessDefinition processDefinition) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        logger.info("启动流程：{}",processInstance.getProcessDefinitionKey());
        return processInstance;
    }

    private static ProcessDefinition getProcessDefinition(ProcessEngine processEngine) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource("BPMN/second_approve.bpmn20.xml");
        Deployment deployment = deploymentBuilder.deploy();
        String deploymentId = deployment.getId();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .singleResult();
        logger.info("流程定义文件:{}，流程ID:{}",processDefinition.getName(),processDefinition.getId());
        return processDefinition;
    }

    private static ProcessEngine getProcessEngine() {
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String name =  processEngine.getName();
        String version = processEngine.VERSION;
        logger.info("流程引擎名称:{}，版本:{}",name,version);
        logger.info("aaa:{}",cfg);
        return processEngine;
    }
}
