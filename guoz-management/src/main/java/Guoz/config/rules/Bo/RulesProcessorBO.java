package Guoz.config.rules.Bo;

import java.io.Serializable;

public class RulesProcessorBO implements Serializable {

    private String projectCode;

    private String userCode;

    private Integer ruleCode;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(Integer ruleCode) {
        this.ruleCode = ruleCode;
    }
}