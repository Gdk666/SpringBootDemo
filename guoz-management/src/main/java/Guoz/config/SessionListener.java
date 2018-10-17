package Guoz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * @description:Session监听器
 * @Data 11:15
 * @Version 1.0
 * @author: Guoz
 **/
@WebListener
public class SessionListener implements HttpSessionListener {
    {

        System.out.println("初始化块");

    }
    private static Logger logger = LoggerFactory.getLogger(SessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session已创建");
        HttpSession session = httpSessionEvent.getSession();
        ServletContext servletContext = session.getServletContext();
        logger.info(String.valueOf(servletContext));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info("Session已销毁");
    }
}
