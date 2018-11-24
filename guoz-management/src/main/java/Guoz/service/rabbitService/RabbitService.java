package Guoz.service.rabbitService;/**
 * @Auther: Guoz666
 * @Date: 2018/11/9 15:23
 * @Description:
 */

import Guoz.pojo.po.Log;
import com.guoz.framework.mybatis.service.BaseService;

/**
 *@description:
 *@Data 15:23
 *@Version 1.0
 *@author: Guoz
 **/
public interface RabbitService extends BaseService<Log>{
    /**
     * rabbit信息发送
     */
    public void defaultMessage();


}
