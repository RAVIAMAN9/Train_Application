package rkj.trainService.trainService.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rkj.Repository.Repo.StationRepository.StationPersistence;
import rkj.Repository.Repo.TrainRepositories.TrainPersistance;
import rkj.objLib.objLib.StationServiceObject.Station;
import rkj.objLib.objLib.TrainServiceObject.Dto.Train;

@Service
public class TrainService {

    @Autowired
    private TrainPersistance trainPersistance;

    private ObjectMapper mapper = new ObjectMapper();

    public String addTrain(Train train) {
        trainPersistance.addTrain(train);
        return "train details added successfully.";
    }

    public Train getTrainDetails(Integer trainNumber) {
        return trainPersistance.getTrainDetails(trainNumber);

    }

}