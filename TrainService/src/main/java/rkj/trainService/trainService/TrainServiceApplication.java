package rkj.trainService.trainService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"rkj.Repository.Repo","rkj.trainService.trainService",
"rkj.clientRepo.clientRepo","rkj.objLib.objLib"})
@EnableFeignClients
public class TrainServiceApplication {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(TrainServiceApplication.class, args);
	}
}
