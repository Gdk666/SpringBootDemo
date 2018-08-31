package Guoz.Controller;


import com.guoz.framework.commons.annotation.CacheLock;
import com.guoz.framework.commons.annotation.CacheParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Data 16:19
 * @Version 1.0
 **/
@RestController
@RequestMapping("/book")
public class BookContrller {
    /*@LocalLock(key = "book:arg[0]")
    @GetMapping
    public String query(@RequestParam String token){
        return "succ"+token;
    }*/


    @GetMapping("/query")
    @CacheLock(prefix = "book")
    public String query2(@CacheParam(name = "token") @RequestParam String token) {
        return "success"+token;
    }
}
