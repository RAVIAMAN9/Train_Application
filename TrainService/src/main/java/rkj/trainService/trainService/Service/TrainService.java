package rkj.trainService.trainService.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rkj.Repository.Repo.TrainRepositories.TrainPersistance;
//import rkj.objLib.objLib.AsynchronousObjects.RabbitMqObjects.TicketEvent;
import rkj.objLib.objLib.ServiceObjects.TrainServiceObject.Dto.Train;
import rkj.objLib.objLib.ServiceObjects.TrainServiceObject.Dto.TrainResponse;

@Service
public class TrainService {

    @Autowired
    private TrainPersistance trainPersistance;

    private ObjectMapper mapper = new ObjectMapper();

    public String addTrain(Train train) {
        trainPersistance.addTrain(train);
        return "train details added successfully.";
    }

    public TrainResponse getTrainDetails(Integer trainNumber) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("rkj"));
        System.out.println(encoder.encode("akj"));
        return trainPersistance.getTrainDetails(trainNumber);

    }

//    public void updateTrain(TicketEvent ticketEvent) {
//        trainPersistance.updateTrain(ticketEvent);
//    }

}