package Guoz.config.rules.context;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import Guoz.config.rules.Bo.selectProcessorBo;
import Guoz.config.rules.RuleStrategy;
import Guoz.config.rules.impl.selectStrategy;

@Component
public class context {

    @Resource
    private RuleStrategy ruleStrategy;

    public context(RuleStrategy ruleStrategy) {
        this.ruleStrategy = ruleStrategy;
    }

    public void contextIface(selectProcessorBo selectProcessorBo){
        ruleStrategy.process(selectProcessorBo);
    }
}