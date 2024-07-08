package rkj.ticketService.ticketService.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rkj.objLib.objLib.StationServiceObject.Station;
import rkj.objLib.objLib.TicketObject.Ticket;
import rkj.objLib.objLib.TicketObject.TicketResponse;
import rkj.ticketService.ticketService.Service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketResource {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/book-ticket")
    public ResponseEntity<TicketResponse> bookTicket(@RequestBody Ticket ticket){
        return new ResponseEntity<TicketResponse>(ticketService.bookTicket(ticket),
                HttpStatus.CREATED);
    }

}
