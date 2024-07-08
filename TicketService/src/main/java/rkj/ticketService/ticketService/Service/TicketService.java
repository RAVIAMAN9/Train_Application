package rkj.ticketService.ticketService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import rkj.Repository.Repo.StationRepository.StationPersistence;
import rkj.Repository.Repo.TicketRepository.TicketPersistence;
import rkj.clientRepo.clientRepo.Clients.TrainClient;
import rkj.objLib.objLib.StationServiceObject.Station;
import rkj.objLib.objLib.TicketObject.Ticket;
import rkj.objLib.objLib.TicketObject.TicketResponse;
import rkj.objLib.objLib.TrainServiceObject.Dto.Train;

@Service
public class TicketService {

    @Autowired
    private TicketPersistence ticketPersistence;

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient webClient;

    @Autowired
    private TrainClient trainClient;

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
        tr.setTrainName(train.getTrainName());
        tr.setBoarding(ticket.getBoarding());
        tr.setDestination(ticket.getDestination());
        return tr;
    }
}
