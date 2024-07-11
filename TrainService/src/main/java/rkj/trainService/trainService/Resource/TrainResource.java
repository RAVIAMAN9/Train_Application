package rkj.trainService.trainService.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rkj.objLib.objLib.TrainServiceObject.Dto.Train;
import rkj.objLib.objLib.TrainServiceObject.Dto.TrainResponse;
import rkj.trainService.trainService.Service.TrainService;

@RefreshScope
@RestController
@RequestMapping("/train")
public class TrainResource {

    @Autowired
    private TrainService trainService;

    @Value("${spring.boot.message}")
    private String message;

    @PostMapping("/add-train")
    public ResponseEntity<String> addTrain(@RequestBody Train train) {
        return new ResponseEntity<String>("added the train with train name: "+trainService.addTrain(train)+" successfully",
                HttpStatus.CREATED);
    }

    @GetMapping("/{trainNumber}")
    public ResponseEntity<TrainResponse> getTrainDetails(@PathVariable Integer trainNumber){
        return new ResponseEntity<TrainResponse>(trainService.getTrainDetails(trainNumber),
                HttpStatus.OK);
    }

    @GetMapping
    public String getMapping(){
        return message;
    }

}
