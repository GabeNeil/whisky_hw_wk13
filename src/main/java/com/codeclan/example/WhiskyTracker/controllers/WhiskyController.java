package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(
            @RequestParam(name="whiskyYear", required = false) Integer age,
            @RequestParam(name="distillery", required = false) String distillery
    ){
        if (age != null){
            return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskiesByAge(age), HttpStatus.OK);
        }
        if (distillery != null){
            return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskiesByDistillery(distillery), HttpStatus.OK);
        }
        else if (distillery != null && age !=null){
            return new ResponseEntity<List<Whisky>>(whiskyRepository.findWhiskiesByAgeAndDistillery(age, distillery), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity getWhisky(@PathVariable Long id){
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

}
