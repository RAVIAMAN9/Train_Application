package rkj.stationService.stationService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rkj.Repository.Repo.StationRepository.StationPersistence;
import rkj.Repository.Repo.StationStoppageRepository.StationStoppagePersistance;
import rkj.objLib.objLib.ServiceObjects.StationServiceObject.Station;
import rkj.objLib.objLib.ServiceObjects.StationStoppageObject.StationStoppage;

import java.util.List;

@Service
public class StationStoppageService {

    @Autowired
    private StationStoppagePersistance stationStoppagePersistance;

    @Autowired
    private StationPersistence stationPersistence;

    public void addStationStoppage(Station station) {
        stationPersistence.addStation(station);
    }

    public List<Integer> getTrainList(String stationCode){
        return stationStoppagePersistance.getTrainNumbers(stationCode);
    }

    public void updateTrainAtStoppage(String stationCode, Integer trainNumber) throws JsonProcessingException {
        stationPersistence.updateTrainStoppage(stationCode,trainNumber);
    }
}
