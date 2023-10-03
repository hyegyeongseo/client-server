package team.chichi.pamback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 스케줄러를 사용하기 위한 어노테이션
@SpringBootApplication
public class PambackApplication {

	public static void main(String[] args) {
		SpringApplication.run(PambackApplication.class, args);
	}

}
