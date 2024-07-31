package rkj.stationService.stationService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rkj.Repository.Repo.StationRepository.StationPersistence;
import rkj.Repository.Repo.StationStoppageRepository.StationStoppagePersistance;
import rkj.clientRepo.clientRepo.AsyncClients.Producer.TrainStoppageProducer;
import rkj.objLib.objLib.AsynchronousObjects.KafkaObjects.TrainStoppage;
import rkj.objLib.objLib.ServiceObjects.StationServiceObject.Station;
import rkj.objLib.objLib.ServiceObjects.StationStoppageObject.StationStoppage;

import java.util.List;

@Service
public class StationStoppageService {

    @Autowired
    private StationStoppagePersistance stationStoppagePersistance;

    @Autowired
    private StationPersistence stationPersistence;

    @Autowired
    private TrainStoppageProducer trainStoppageProducer;

    public void addStationStoppage(Station station) {
        stationPersistence.addStation(station);
    }

    public Station getTrainList(String stationCode){
        //List<Integer> i = stationPersistence.getStation(stationCode);
        return stationPersistence.getStationDetails(stationCode);
    }

    public void updateTrainAtStoppage(String stationCode, Integer trainNumber) throws JsonProcessingException {
        //stationPersistence.updateTrainStoppage(stationCode,trainNumber);
        TrainStoppage ts = new TrainStoppage();
        ts.setStoppageCode(stationCode);
        ts.setTrainNumber(trainNumber);
        trainStoppageProducer.sendMessage(ts);
    }
}
