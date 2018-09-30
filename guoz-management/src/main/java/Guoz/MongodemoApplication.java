package Guoz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import tk.mybatis.spring.annotation.MapperScan;

import java.time.Duration;


@SpringBootApplication
/*@EnableScheduling //定时任务
*/
@ComponentScan("Guoz")
@EnableCaching
@EnableRedisHttpSession
public class MongodemoApplication {

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


