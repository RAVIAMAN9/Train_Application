package rkj.ticketService.ticketService.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rkj.objLib.objLib.ServiceObjects.TicketObject.Ticket;
import rkj.objLib.objLib.ServiceObjects.TicketObject.TicketResponse;
import rkj.ticketService.ticketService.Service.TicketService;

@RefreshScope
@RestController
@RequestMapping("/ticket")
public class TicketResource {

    @Value("${spring.boot.message}")
    private String message;

    @Autowired
    private TicketService ticketService;

    @PostMapping("/book-ticket")
    public ResponseEntity<TicketResponse> bookTicket(@RequestBody Ticket ticket){
        return new ResponseEntity<TicketResponse>(ticketService.bookTicket(ticket),
                HttpStatus.CREATED);
    }

    @DeleteMapping("cancel/{pnrNumber}")
    public String deleteTicket(@PathVariable Integer pnrNumber) throws JsonProcessingException {
        ticketService.cancelTicket(pnrNumber);
        return "success";
    }

    @GetMapping
    public String getMapping(){
        return message;
    }

}
