package rkj.stationService.stationService.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rkj.objLib.objLib.Exception.ExceptionObjects.StationException;
import rkj.objLib.objLib.ServiceObjects.StationServiceObject.Station;
import rkj.objLib.objLib.ServiceObjects.StationStoppageObject.StationStoppage;
import rkj.stationService.stationService.Service.StationStoppageService;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/station")
public class StationStoppageResource {

//    @Autowired
//    private StationService stationService;

    @Value("${spring.boot.message}")
    private String message;
    @Autowired
    private StationStoppageService stationStoppageService;

    @PostMapping("/add-station")
    public ResponseEntity<String> addStationStoppage(@RequestBody Station station) throws StationException {
        stationStoppageService.addStationStoppage(station);
        return new ResponseEntity<String>("station added :"+station.getStationCode(), HttpStatus.CREATED);
    }

    @GetMapping("/{stationCode}")
    public ResponseEntity<Station> getTrainList(@PathVariable String stationCode){
        return new ResponseEntity<Station>(stationStoppageService.getTrainList(stationCode),HttpStatus.FOUND);
    }

    @PatchMapping("/update")
    public String updateTrainAtStoppage(@RequestParam("stationCode") String stationCode,
                                        @RequestParam("trainNumber") Integer trainNumber) throws JsonProcessingException {
        stationStoppageService.updateTrainAtStoppage(stationCode, trainNumber);
        return "success";
    }

//    @PostMapping("/add-station")
//    public ResponseEntity<String> addStation(@RequestBody Station station) {
//        return new ResponseEntity<String>("Details for the station "+stationService.addStation(station)+" added successfully", HttpStatus.CREATED);
//    }

    @GetMapping
    public String getMapping(){
        return message;
    }
}
