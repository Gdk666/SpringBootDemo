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
		SpringApplication.run(MongodemoApplication.class, args);
	}
}


