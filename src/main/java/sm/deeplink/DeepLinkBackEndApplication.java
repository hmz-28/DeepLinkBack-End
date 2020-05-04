package sm.deeplink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class DeepLinkBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeepLinkBackEndApplication.class, args);

	}

}
