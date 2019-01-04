/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package Guoz.config.rules;

import Guoz.config.rules.Bo.RulesProcessorBO;

/**
 * TODO
 *
 * @author 1
 * @version V1.0
 * @since 2018-12-25 11:08
 */
public interface RuleStrategy<T extends RulesProcessorBO> {
    
    /**
     *
     * 功能描述: 
     *
     * @param:
     * @return: 
     * @auther: Guoz666
     * @date: 2018/12/25 11:09
     */
    void process(T t);
}
