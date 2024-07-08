package rkj.stationService.stationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"rkj.Repository.Repo","rkj.stationService.stationService",
"rkj.clientRepo.clientRepo"})
@EnableFeignClients
public class StationServiceApplication {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(StationServiceApplication.class, args);
	}
}
