package rkj.ticketService.ticketService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import rkj.Repository.Repo.StationRepository.StationPersistence;
import rkj.Repository.Repo.TicketRepository.TicketPersistence;
import rkj.clientRepo.clientRepo.Clients.TrainClient;
import rkj.clientRepo.clientRepo.RabbitMQClients.Producer.TicketProducer;
import rkj.objLib.objLib.AsynchronousObjects.RabbitMqObjects.TicketEvent;
import rkj.objLib.objLib.ServiceObjects.TicketObject.Ticket;
import rkj.objLib.objLib.ServiceObjects.TicketObject.TicketEntity;
import rkj.objLib.objLib.ServiceObjects.TicketObject.TicketResponse;
import rkj.objLib.objLib.ServiceObjects.TrainServiceObject.Dto.Train;

import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketPersistence ticketPersistence;

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private TicketProducer ticketProducer;
//    @Autowired
//    private WebClient webClient;

    @Autowired
    private TrainClient trainClient;

    @Autowired
    private TicketProducer ticketProducer;

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultTrainDetails")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultTrainDetails")
    public TicketResponse bookTicket(Ticket ticket){
        //String uri = "http://localhost:8080/train/"+ticket.getTrainNumber();
//        Train train = restTemplate.getForEntity("http://localhost:8080/train/"+ticket.getTrainNumber(),
//               Train.class ).getBody();
//        Train train = webClient.get()
//                .uri(uri)
//                .retrieve()
//                .bodyToMono(Train.class)
//                .block();
        Train train = trainClient.getTrainDetails(ticket.getTrainNumber());
        TicketResponse tr = ticketPersistence.addTicket(ticket);
        TicketEvent te = new TicketEvent();
        te.setTrainNumber(tr.getTrainNumber());
        te.setCoachType(ticket.getCoachType());
        te.setIsCancellable(0);
        te.setNumberOfSeats(ticket.getPassengerList().size());
        produceTicketEvent(te);
        tr.setTrainName(train.getTrainName());
        tr.setBoarding(ticket.getBoarding());
        tr.setDestination(ticket.getDestination());
        return tr;
    }

    public TicketResponse getDefaultTrainDetails(Ticket ticket, Exception exception){
        Train train = new Train();
        train.setTrainNumber(12345);
        train.setTrainName("NA");
        train.setSource("NA");
        train.setDestination("NA");
        train.setTrainType("EXPRESS");
        TicketResponse tr = ticketPersistence.addTicket(ticket);
        tr.setTrainName(train.getTrainName());
        tr.setBoarding(ticket.getBoarding());
        tr.setDestination(ticket.getDestination());
        return tr;
    }

    private void produceTicketEvent(TicketEvent ticketEvent){
        ticketProducer.sendMessage(ticketEvent);
    }

    public void cancelTicket(Integer pnrNumber) throws JsonProcessingException {
        Ticket t = ticketPersistence.getTicketDetails(pnrNumber);
        TicketEvent te = new TicketEvent();
        te.setTrainNumber(t.getTrainNumber());
        te.setCoachType(t.getCoachType());
        te.setIsCancellable(1);
        te.setNumberOfSeats(t.getPassengerList().size());
        produceTicketEvent(te);
        ticketPersistence.updateTicketStatus(pnrNumber);
    }


}
