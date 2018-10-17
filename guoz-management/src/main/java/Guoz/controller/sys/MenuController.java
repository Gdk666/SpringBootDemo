package Guoz.controller.sys;


import Guoz.controller.BaseController;
import Guoz.pojo.message.ApiResult;
import Guoz.pojo.po.Menu;
import Guoz.service.MenuService;
import com.google.common.collect.Lists;
import com.guoz.framework.commons.annotation.GuozLog;
import com.guoz.framework.mybatis.page.DataGrid;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import static com.guoz.framework.commons.Exception.BattcnException.notFound;


/**
 * @author Guoz
 */
@Controller
@RequestMapping("/sys/menu")
@Api(value = "菜单管理")
@ApiIgnore
public class MenuController extends BaseController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/list")
    public String list(DataGrid grid) {
        request.setAttribute("page", this.menuService.listMenuForDataGrid(grid));
        return "sys/menu/list";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id) {
        if (id != null) {
            request.setAttribute("dto", this.menuService.selectById(id).orElseThrow(() -> notFound("该数据已失效")));
        }
        request.setAttribute("menus", this.menuService.listMenu());
        return "sys/menu/edit";
    }

    @GuozLog(module = "菜单管理", methods = "保存菜单", description = "添加/修改菜单信息")
    @PostMapping(value = "/save")
    @ResponseBody
    public ApiResult<String> save(Menu menu) {
        if (menu != null) {
            this.menuService.saveOrUpdate(menu);
        }
        return ApiResult.SUCCESS;
    }

    @GuozLog(module = "菜单管理", methods = "移除菜单", description = "删除菜单信息")
    @PostMapping(value = "/remove")
    @ResponseBody
    public ApiResult<String> del(Integer[] ids) {
        Lists.newArrayList(ids).forEach(this.menuService::deleteById);
        return ApiResult.SUCCESS;
    }


}
