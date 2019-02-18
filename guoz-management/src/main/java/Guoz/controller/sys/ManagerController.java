package Guoz.controller.sys;

import com.Guoz.Dubbo.Service.RemoteService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.guoz.framework.commons.Exception.BattcnException;
import com.guoz.framework.commons.annotation.GuozLog;
import com.guoz.framework.mybatis.page.DataGrid;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Guoz.controller.BaseController;
import Guoz.pojo.dto.ManagerDto;
import Guoz.pojo.message.ApiResult;
import Guoz.pojo.po.Manager;
import Guoz.service.ManagerService;
import Guoz.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


/**
 * @author Guoz
 */
@Controller
@RequestMapping("/sys/manager")
@Api(value = "用户管理", description = "用户管理", tags = "1.2")
public class ManagerController extends BaseController {

    private final ManagerService managerService;
    private final RoleService roleService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;


    @Autowired
    public ManagerController(ManagerService managerService, RoleService roleService) {
        this.managerService = managerService;
        this.roleService = roleService;
    }

    @ApiIgnore
    @GetMapping("/list")
    public String list() {
        return "sys/manager/list";
    }

    @ApiIgnore
    @GetMapping(value = "/edit")
    public String edit(Integer id) {
        if (id != null) {
            request.setAttribute("dto", this.managerService.selectById(id).orElseThrow(() -> BattcnException.notFound("该数据已失效")));
        }
        request.setAttribute("roles", this.roleService.listRole());
        return "sys/manager/edit";
    }

    @ApiOperation(value = "带条件分页查询")
    @GetMapping(value = "/query")
    @ResponseBody
    public PageInfo<ManagerDto> query(DataGrid grid, String name, String account) {
        return this.managerService.listManagerByName(grid, name, account);
    }

    @ApiOperation(value = "添加/修改用户信息")
    @GuozLog(module = "用户管理", methods = "保存用户", description = "添加/修改用户信息")
    @PostMapping(value = "/save")
    @ResponseBody
    public ApiResult<Manager> save(Manager dto) {
        dto.setGmtModified(new Date());
        if (dto.getId() != null) {
            return ApiResult.getResponse(this.managerService.updateSelectiveById(dto));
        }
        return ApiResult.getResponse(this.managerService.insertSelective(dto));
    }

    @ApiOperation(value = "删除用户信息")
    @GuozLog(module = "用户管理", methods = "移除用户", description = "删除用户信息")
    @PostMapping(value = "/remove")
    @ResponseBody
    public ApiResult<String> del(Integer[] ids) {
        Lists.newArrayList(ids).forEach(this.managerService::deleteById);
        return ApiResult.SUCCESS;
    }
    @Cacheable(value="cacheName", key ="'manager_'+#id")
    @GetMapping("/get/{id}")
    public String getUser(@PathVariable int id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Manager manager = this.managerService.getById(String.valueOf(id));
        mav.addObject("author",manager);
        request.getSession().setAttribute("manager",manager);
        redisTemplate.opsForValue().set("h2", "123-123".split("-")[0]);
        return "hello";
    }

    @RequestMapping("/activiti")
    public void helloWorld() {
        //根据bpmn文件部署流程
        Deployment deploy = repositoryService.createDeployment()
            .addClasspathResource("BPMN/testApprove.bpmn")
            .deploy();
        //获取流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
        //启动流程定义，返回流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
        String processId = pi.getId();
        System.out.println("流程创建成功，当前流程实例ID："+processId);
        Task task=taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("执行前，任务名称："+task.getName());
        taskService.complete(task.getId());
        task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        logger.info("流程定义文件:{}，流程ID:{}",processDefinition.getName(),processDefinition.getId());
        System.out.println("task为null，任务执行完毕："+task);



    }


}
