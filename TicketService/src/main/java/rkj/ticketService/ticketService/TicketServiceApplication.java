package rkj.ticketService.ticketService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"rkj.Repository.Repo","rkj.ticketService.ticketService",
"rkj.clientRepo.clientRepo","rkj.objLib.objLib"})
@EnableFeignClients
public class TicketServiceApplication {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}
}
