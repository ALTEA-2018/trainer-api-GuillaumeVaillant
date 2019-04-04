package com.miage.altea.tp.trainer_api.controller;

import com.miage.altea.tp.trainer_api.bo.Trainer;
import com.miage.altea.tp.trainer_api.service.TrainerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/trainers")
public class TrainerController {


    private final TrainerService trainerService;

    TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    public Iterable<Trainer> getAllTrainers(){
        // TODO
        return this.trainerService.getAllTrainers();
    }

    @GetMapping("/{name}")
    public Trainer getTrainer(@PathVariable String name){
        // TODO
        return this.trainerService.getTrainer(name);
    }

    @PostMapping("/")
    public Trainer createTrainer(@RequestBody Trainer trainer){
        // TODO
        return this.trainerService.createTrainer(trainer);
    }
}