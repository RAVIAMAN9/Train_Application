package rkj.trainService.trainService.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rkj.objLib.objLib.TrainServiceObject.Dto.Train;
import rkj.trainService.trainService.Service.TrainService;

@RestController
@RequestMapping("/train")
public class TrainResource {

    @Autowired
    private TrainService trainService;

    @PostMapping("/add-train")
    public ResponseEntity<String> addTrain(@RequestBody Train train) {
        return new ResponseEntity<String>("added the train with train name: "+trainService.addTrain(train)+" successfully",
                HttpStatus.CREATED);
    }

    @GetMapping("/{trainNumber}")
    public ResponseEntity<Train> getTrainDetails(@PathVariable Integer trainNumber){
        return new ResponseEntity<Train>(trainService.getTrainDetails(trainNumber),
                HttpStatus.OK);
    }

}
