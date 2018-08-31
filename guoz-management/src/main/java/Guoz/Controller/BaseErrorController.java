package Guoz.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @Data 10:44
 * @Version 1.0
 * @author: 1
 **/

@Controller
@RequestMapping(value = "error")
public class BaseErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error/error";
    }
    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}
