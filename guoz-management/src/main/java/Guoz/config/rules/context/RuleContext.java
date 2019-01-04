package Guoz.config.rules.context;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import Guoz.config.rules.Bo.RulesProcessorBO;
import Guoz.config.rules.factory.rulesFactory;

@Component
public class RuleContext {

    @Resource
    private rulesFactory bizRuleFactory;

    public void process(RulesProcessorBO rulesProcessorBO) {

        bizRuleFactory.creator(rulesProcessorBO.getRuleCode()).process(rulesProcessorBO);

    }

}