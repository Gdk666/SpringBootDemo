package Guoz.config.rules.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import Guoz.config.rules.Bo.selectProcessorBo;
import Guoz.config.rules.RuleStrategy;
import Guoz.mapper.ManagerMapper;
import Guoz.pojo.po.Manager;
import Guoz.service.ManagerService;

/**
 * TODO
 *
 * @author 1
 * @version V1.0
 * @since 2018-12-25 11:13
 */
@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class selectStrategy implements RuleStrategy<selectProcessorBo> {
    /**
     *
     * 加载顺序的问题，@autowired写在变量上的注入要等到类完全加载完，才会将相应的bean注入，
     * 而变量是在加载类的时候按照相应顺序加载的，所以变量的加载要早于@autowired变量的加载，
     * 那么给变量managerMapper 赋值的时候所使用的managerMapper，其实还没有被注入，所以报空指针，而使用构造器就在加载类的时候将managerMapper加载了
     ---------------------

     */

    private final ManagerService managerMapper;

    @Autowired
    public selectStrategy(ManagerService managerMapper) {
        this.managerMapper= managerMapper;
    }

    @Override
    public void process(selectProcessorBo rulesProcessorBO) {
        managerMapper.getById("1");
        System.out.println("123");

    }
}
