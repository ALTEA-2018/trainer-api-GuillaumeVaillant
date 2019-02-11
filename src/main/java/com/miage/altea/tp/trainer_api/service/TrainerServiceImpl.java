package com.miage.altea.tp.trainer_api.service;

import com.miage.altea.tp.trainer_api.bo.Trainer;
import com.miage.altea.tp.trainer_api.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Iterable<Trainer> getAllTrainers() {
        // TODO

        return this.trainerRepository.findAll();
    }

    @Override
    public Trainer getTrainer(String name) {
        // TODO
        Optional<Trainer> trainer = this.trainerRepository.findById(name);

        return trainer.isPresent() ? trainer.get() : null;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        // TODO

        return this.trainerRepository.save(trainer);
    }
/*
    @Override
    public Trainer updateTrainer(Trainer trainer) {
        // TODO

        return this.trainerRepository.save(trainer);
    }

    @Override
    public boolean deleteTrainer(String name) {
        // TODO

        this.trainerRepository.deleteById(name);

        return trainerRepository.findById(name).isPresent();
    } */
}