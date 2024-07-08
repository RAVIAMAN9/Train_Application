package rkj.stationService.stationService.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rkj.objLib.objLib.StationServiceObject.Station;
import rkj.stationService.stationService.Service.StationService;

@RestController
@RequestMapping("/station")
public class StationResource {

    @Autowired
    private StationService stationService;

    @PostMapping("/add-station")
    public ResponseEntity<String> addStation(@RequestBody Station station) {
        return new ResponseEntity<String>("Details for the station "+stationService.addStation(station)+" added successfully", HttpStatus.CREATED);
    }
}
