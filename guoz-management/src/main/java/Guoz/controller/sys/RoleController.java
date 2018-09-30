package Guoz.controller.sys;


import Guoz.controller.BaseController;
import Guoz.pojo.message.ApiResult;
import Guoz.pojo.po.Role;
import Guoz.service.MenuService;
import Guoz.service.RoleService;
import com.github.pagehelper.PageInfo;
import com.guoz.framework.commons.annotation.BattcnLog;
import com.guoz.framework.mybatis.page.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

import static com.guoz.framework.commons.Exception.BattcnException.notFound;


/**
 * @author Guoz
 */
@Controller
@RequestMapping("/sys/role")
@ApiIgnore
public class RoleController extends BaseController {

    private final RoleService roleService;
    private final MenuService menuService;

    @Autowired
    public RoleController(RoleService roleService, MenuService menuService) {
        this.roleService = roleService;
        this.menuService = menuService;
    }


    @GetMapping("/list")
    public String list() {
        return "sys/role/list";
    }

    @GetMapping("permissions")
    public String permissions(Integer roleId) {
        request.setAttribute("permissions", this.menuService.listPermissions());
        request.setAttribute("roleId", roleId);
        return "sys/role/permissions";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id) {
        if (id != null) {
            request.setAttribute("dto", this.roleService.selectById(id).orElseThrow(() -> notFound("未找到记录")));
        }
        return "sys/role/edit";
    }

    @GetMapping(value = "/query")
    @ResponseBody
    public PageInfo<Role> query(DataGrid grid, String name, String account) {
        return this.roleService.listForDataGrid(grid);
    }

    @BattcnLog(module = "角色管理", methods = "保存角色", description = "添加/修改角色信息")
    @PostMapping(value = "/save")
    @ResponseBody
    public ApiResult<String> save(Role dto) {
        dto.setGmtModified(new Date());
        if (dto.getId() != null) {
            return ApiResult.getResponse(this.roleService.updateSelectiveById(dto));
        }
        return ApiResult.getResponse(this.roleService.insertSelective(dto));
    }

    @BattcnLog(module = "角色管理", methods = "移除角色", description = "删除角色信息")
    @PostMapping(value = "/remove")
    @ResponseBody
    public ApiResult<String> del(Integer[] ids) {
        this.roleService.deleteRoleAndOperate(ids);
        return ApiResult.SUCCESS;
    }

}
