package com.heartpredictorservice.controller;

import com.heartpredictorservice.model.PatientReport;
import com.heartpredictorservice.model.Response;
import com.heartpredictorservice.service.PreditctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/v1/heart/disease")
public class HeartController {

    private PreditctorService preditctorService;
    public HeartController(PreditctorService preditctorService){
        this.preditctorService = preditctorService;
    }

    @PostMapping("/predict")
    public ResponseEntity<Response> getChanceOfHeartDisease(@RequestBody PatientReport patientReport) throws FileNotFoundException {
       boolean isPresent =  preditctorService.predictHeartdisease(patientReport);
        Response response = new Response();
       if(isPresent){
           response.setCode(100);
           response.setMessage("Heart disease is present");
       }else {
           response.setCode(000);
           response.setMessage("Heart disease not present");
       }
       return  ResponseEntity.ok(response);
    }
}
