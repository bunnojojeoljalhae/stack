package music.playlist.stack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
//@EnableJpaAuditing
//@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
//@EnableJdbcHttpSession(tableName = "SPRING_SESSION_ATTRIBUTES")
public class StackApplication {

	public static void main(String[] args) {

		System.setProperty("server.servlet.context-path", "/");
		SpringApplication.run(StackApplication.class, args);

	}

}
