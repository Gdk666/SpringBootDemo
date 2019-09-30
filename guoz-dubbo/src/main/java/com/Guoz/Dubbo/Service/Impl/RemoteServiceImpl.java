package com.Guoz.Dubbo.Service.Impl;

import com.Guoz.Dubbo.Service.RemoteService;
import com.alibaba.dubbo.config.annotation.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 1
 * @version V1.0
 * @since 2019-01-21 18:55
 */
@Component
@Service(timeout = 10000,interfaceClass = RemoteService.class)
public class RemoteServiceImpl implements RemoteService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public String  sayHello(String msg) {
        return  msg;
    }
}
