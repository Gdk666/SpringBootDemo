package Guoz;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

//禁止加载自身对DataSource的扫描DataSourceAutoConfiguration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
/*@EnableScheduling //定时任务
*/
@ComponentScan("Guoz")
@EnableCaching
@ServletComponentScan
public class MongodemoApplication {
	public static Logger logger = LoggerFactory.getLogger(MongodemoApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MongodemoApplication.class, args);
		SpringContextUtil.setApplicationContext(context);
	}

	/*@Bean
	public TaskScheduler taskScheduler(){
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(10);
		return threadPoolTaskScheduler;
	}
	@Bean
	public CacheKeyGenerator cacheKeyGenerator() {
		return new LockKeyGenerator();
	}*/

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	static public class SpringContextUtil {

		private static ApplicationContext applicationContext;

		public static ApplicationContext getApplicationContext() {
			return applicationContext;
		}

		private static void setApplicationContext(ApplicationContext applicationContext) {
			SpringContextUtil.applicationContext = applicationContext;
		}

		public static Object getBean(String name) {
			return applicationContext.getBean(name);
		}

		public static <T> T getBean(Class<T> requiredType) {
			return applicationContext.getBean(requiredType);
		}
	}



}


