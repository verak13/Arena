package arena.arena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAutoConfiguration
@SpringBootApplication(exclude = ContextInstanceDataAutoConfiguration.class)
@EnableAsync
public class ArenaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArenaApplication.class, args);
	}
}
