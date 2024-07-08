package rkj.stationService.stationService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rkj.Repository.Repo.StationStoppageRepository.StationStoppagePersistance;
import rkj.objLib.objLib.StationStoppageObject.StationStoppage;

import java.util.List;

@Service
public class StationStoppageService {

    @Autowired
    private StationStoppagePersistance stationStoppagePersistance;

    public void addStationStoppage(StationStoppage stationStoppage) {
        stationStoppagePersistance.addStationStoppage(stationStoppage);
    }

    public List<Integer> getTrainList(String stationCode){
        return stationStoppagePersistance.getTrainNumbers(stationCode);
    }
}
