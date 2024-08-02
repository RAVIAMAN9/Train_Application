package rkj.trainService.trainService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rkj.Repository.Repo.TrainRepositories.TrainPersistance;
//import rkj.objLib.objLib.AsynchronousObjects.RabbitMqObjects.TicketEvent;
import rkj.Repository.Repo.TrainRepositories.TrainRepo;
import rkj.clientRepo.clientRepo.AsyncClients.Producer.TrainStoppageProducer;
import rkj.objLib.objLib.AsynchronousObjects.KafkaObjects.TrainStoppage;
import rkj.objLib.objLib.AsynchronousObjects.RabbitMqObjects.TicketEvent;
import rkj.objLib.objLib.Exception.ExceptionObjects.TrainException;
import rkj.objLib.objLib.ServiceObjects.TrainServiceObject.Dto.Train;
import rkj.objLib.objLib.ServiceObjects.TrainServiceObject.Dto.TrainResponse;

@Service
public class TrainService {

    @Autowired
    private TrainPersistance trainPersistance;

    @Autowired
    private TrainStoppageProducer tsp;

    @Autowired
    TrainRepo tr;

    private ObjectMapper mapper = new ObjectMapper();

    public String addTrain(Train train) throws TrainException {
        trainPersistance.addTrain(train);
        return "train details added successfully.";
    }

    public TrainResponse getTrainDetails(Integer trainNumber) {
        return trainPersistance.getTrainDetails(trainNumber);
    }

    public void updateTrain(Integer trainNumber, String stationCode) throws JsonProcessingException {
        TrainStoppage ts = new TrainStoppage();
        ts.setTrainNumber(trainNumber);
        ts.setStoppageCode(stationCode);
        tsp.sendMessage(ts);
    }
}