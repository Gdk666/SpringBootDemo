package Guoz.config.activitiConfig;

import org.activiti.engine.*;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;




@Configuration
public class activitiConfig {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private ResourcePatternResolver resourceLoader;

    @Bean
    public StandaloneProcessEngineConfiguration processEngineConfiguration() {

        StandaloneProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        //默认H2
        //2.jdbc
        //3.配置第三方数据源 Druid、Dbcp、HikariCP（Spring默选）
        configuration.setDataSource(dataSource);
        //false:启动时检查数据库版本，发生不匹配抛异常
        //true:启东市自动检查并更新，不存在会创建
        //create-drop：启动创建数据库表结构，结束时删除表结构
       // configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP);
        configuration.setAsyncExecutorActivate(false);

        return configuration;
    }

    @Bean
    public ProcessEngine processEngine() {
        return processEngineConfiguration().buildProcessEngine();
    }

    @Bean
    public RepositoryService repositoryService() {
        return processEngine().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() {
        return processEngine().getRuntimeService();
    }

    @Bean
    public TaskService taskService() {
        return processEngine().getTaskService();
    }

}

