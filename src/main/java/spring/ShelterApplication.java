package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ShelterApplication {

	private static final Logger log = LoggerFactory.getLogger(ShelterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ShelterApplication.class, args);
	}
}
