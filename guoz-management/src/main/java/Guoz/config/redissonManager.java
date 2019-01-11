package Guoz.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @Data 11:49
 * @Version 1.0
 * @author: Guoz
 **/
public class redissonManager  {
    private static Config config = new Config();
    private static Redisson redisson = null;

    static {
        config.useSingleServer().setAddress("127.0.0.1:6379");
        redisson = (Redisson) Redisson.create(config);
        try {
            redisson.getConfig().toJSON().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Redisson getRedisson() {
        return redisson;
    }
}
