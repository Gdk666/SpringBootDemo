package Guoz.config.rules.factory;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import Guoz.config.rules.RuleStrategy;
import Guoz.config.rules.impl.selectStrategy;

/**
 * TODO
 *
 * @author 1
 * @version V1.0
 * @since 2018-12-25 11:23
 */
@Component
public class rulesFactory {
    private static final Map<Integer,RuleStrategy> map = new HashMap<>(20);

    @Resource
    public selectStrategy selectStrategy;

    //@PostConstruct 用来修饰一个非静态的void()方法.而且这个方法不能有抛出异常声明。在服务器加载Servlet的时候运行,并且只会被服务器调用一次
    @PostConstruct
    public void init(){
        map.put(1,selectStrategy);
    }

    public RuleStrategy creator(Integer type) {
        return map.get(type);
    }
}
