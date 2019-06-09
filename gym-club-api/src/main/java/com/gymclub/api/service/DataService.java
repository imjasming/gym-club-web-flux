package com.gymclub.api.service;

import com.gymclub.api.domain.Trainer;
import reactor.core.publisher.Flux;

/**
 * @author Xiaoming.
 * Created on 2019/03/29 18:21.
 */
public interface DataService {

    Flux<Trainer> getUserTrainers(String uname);

    void addUserTrainerByID(String uname, Integer id);
}
