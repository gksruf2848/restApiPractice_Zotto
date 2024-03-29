package org.tain;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.tools.properties.ProjEnvBase;
import org.tain.utils.CurrentInfo;
import org.tain.working.Working;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class RestApiPractice09Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("HANLIM-20220404 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(RestApiPractice09Application.class, args);
	}

	@Autowired
	private ProjEnvBase projEnvBase;
	
	@Autowired
	private Working working;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("HANLIM-20220404 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			try {
				this.working.doing();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Boolean.TRUE) {
			if (this.projEnvBase.isTestFlag()) {
				System.exit(0);
			}
		}
	}
}
