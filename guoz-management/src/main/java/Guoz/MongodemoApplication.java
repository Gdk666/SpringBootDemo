package Guoz;



import Guoz.Service.CacheKeyGenerator;
import Guoz.Service.serviceimpl.LockKeyGenerator;
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
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import tk.mybatis.spring.annotation.MapperScan;

import java.time.Duration;


@MapperScan(basePackages ="Guoz.Dao")  //自动扫描Dao下的接口
@SpringBootApplication
@EnableScheduling //定时任务
@EnableCaching
public class MongodemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodemoApplication.class, args);
	}

	@Bean
	public TaskScheduler taskScheduler(){
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(10);
		return threadPoolTaskScheduler;
	}
	@Bean
	public CacheKeyGenerator cacheKeyGenerator() {
		return new LockKeyGenerator();
	}

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


