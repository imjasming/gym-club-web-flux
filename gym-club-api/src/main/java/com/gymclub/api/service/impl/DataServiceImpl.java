package com.gymclub.api.service.impl;


import com.gymclub.api.domain.Trainer;
import com.gymclub.api.domain.UmUser;
import com.gymclub.api.repository.GymRepository;
import com.gymclub.api.repository.MyRoleRepository;
import com.gymclub.api.repository.TrainerRepository;
import com.gymclub.api.repository.UserRepository;
import com.gymclub.api.repository.EquipmentRepository;
import com.gymclub.api.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Xiaoming.
 * Created on 2019/03/29 20:46.
 */
@Service("dataService")
public class DataServiceImpl implements DataService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyRoleRepository roleRepository;

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public Flux<Trainer> getUserTrainers(String uname) {
        List<Trainer> trainers = userRepository.findByUsername(uname).block().getTrainers();
        return Flux.fromIterable(trainers);
    }

    @Override
    public void addUserTrainerByID(String uname, Integer id) {
        Mono<UmUser> umUser;
        umUser = userRepository.findByUsername(uname);
        umUser.block().getTrainers().add(trainerRepository.findById(id).block());
        userRepository.save(umUser.block());
    }

}
