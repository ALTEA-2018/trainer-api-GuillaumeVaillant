package com.miage.altea.tp.trainer_api.controller;

import com.miage.altea.tp.trainer_api.bo.Trainer;
import com.miage.altea.tp.trainer_api.service.TrainerService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/trainers")
public class TrainerController {

    @Autowired
    private final TrainerService trainerService;

    TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    Iterable<Trainer> getAllTrainers(){
        // TODO
        return this.trainerService.getAllTrainers();
    }

    @GetMapping("/{name}")
    Trainer getTrainer(@PathVariable String name){
        // TODO
        return this.trainerService.getTrainer(name);
    }

    @PostMapping("/")
    Trainer createTrainer(@RequestBody Trainer trainer){
        // TODO
        return this.trainerService.createTrainer(trainer);
    }

    /*
    @PutMapping("/")
    Trainer updateTrainer(@RequestBody Trainer trainer){
        return this.trainerService.updateTrainer(trainer);
    }

    @DeleteMapping("/{name}")
    void deleteTrainer(@PathVariable String name){
        this.trainerService.deleteTrainer(name);
    }

    */
}