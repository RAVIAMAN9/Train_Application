package rkj.stationService.stationService.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rkj.objLib.objLib.StationServiceObject.Station;
import rkj.objLib.objLib.StationStoppageObject.StationStoppage;
import rkj.stationService.stationService.Service.StationService;
import rkj.stationService.stationService.Service.StationStoppageService;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationStoppageResource {

    @Autowired
    private StationService stationService;

    @Autowired
    private StationStoppageService stationStoppageService;

    @PostMapping("/add-train-list")
    public ResponseEntity<String> addStationStoppage(@RequestBody StationStoppage stationStoppage) {
        stationStoppageService.addStationStoppage(stationStoppage);
        return new ResponseEntity<String>("station added :"+stationStoppage.getStationCode(), HttpStatus.CREATED);
    }

    @GetMapping("/{stationCode}")
    public ResponseEntity<List<Integer>> getTrainList(@PathVariable String stationCode){
        return new ResponseEntity<List<Integer>>(stationStoppageService.getTrainList(stationCode),HttpStatus.FOUND);
    }

    @PostMapping("/add-station")
    public ResponseEntity<String> addStation(@RequestBody Station station) {
        return new ResponseEntity<String>("Details for the station "+stationService.addStation(station)+" added successfully", HttpStatus.CREATED);
    }
}
