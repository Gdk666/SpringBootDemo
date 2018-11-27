package Guoz.controller.sys;

import com.alibaba.fastjson.JSON;
import Guoz.controller.BaseController;
import Guoz.pojo.message.ApiResult;
import Guoz.pojo.po.RoleOperate;
import Guoz.service.RoleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author Guoz
 */
@Controller
@RequestMapping("/sys/role/operate/")
@ApiIgnore
public class RoleOperateController extends BaseController {

    private final RoleOperateService roleOperateService;

    @Autowired
    public RoleOperateController(RoleOperateService roleOperateService) {
        this.roleOperateService = roleOperateService;
    }

    @GetMapping("{roleId}")
    @ResponseBody
    public ApiResult<List<RoleOperate>> listOperate(@PathVariable Integer roleId) {
        return ApiResult.getSuccess(this.roleOperateService.listRoleOperateByRoleId(roleId));
    }

    @PostMapping("permissions")
    @ResponseBody
    public ApiResult<String> savePermissions(Integer[] operateId, Integer roleId) {
        logger.debug("[数据] - [{}] - [{}]", JSON.toJSONString(operateId), roleId);
        return this.roleOperateService.batchInsertRoleOperate(operateId, roleId);
    }

}
