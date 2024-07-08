package rkj.trainService.trainService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rkj.Repository.Repo.StationRepository.StationPersistence;
import rkj.objLib.objLib.StationServiceObject.Station;

@Service
public class TrainService {

    @Autowired
    private StationPersistence stationPersistence;


    public String addStation(Station station){
        stationPersistence.addStation(station);
        System.out.println("Hi");
        return station.getStationCode();
    }
}
