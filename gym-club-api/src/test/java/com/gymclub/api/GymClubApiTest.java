package com.gymclub.api;

import com.gymclub.api.domain.Gym;
import com.gymclub.api.domain.Trainer;
import com.gymclub.api.repository.GymRepository;
import com.gymclub.api.repository.MyRoleRepository;
import com.gymclub.api.repository.TrainerRepository;
import com.gymclub.api.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.publisher.Flux;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Xiaoming.
 * Created on 2019/06/09 23:31.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GymClubApiTest {
    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyRoleRepository roleRepository;

    @Test
    public void initData(){
        List<Gym> gyms = new LinkedList<>();
        int k = 1;
        for (char i = 'A', j = '北', l = '身'; i <= 'z'; i++, j++, k++, l++) {
            Gym gym = new Gym();
            gym.setGymName(new StringBuilder("深邃黑暗健").append(l).append("房").toString());
            gym.setLocation("" + "交大东路" + k + "号");
            gym.setIntro("重要事情说三遍，程威沙雕，程威沙雕，程威沙雕");
            gyms.add(gym);
        }
        List<Trainer> ts = new LinkedList<>();
        k = 1;
        for (char i = 'a', j = '北', l = '四'; k <= 30; i++, j++, k++, l++) {
            Trainer trainer = new Trainer();
            trainer.setName(new StringBuilder("李").append(l).toString());
            trainer.setAge(k);
            trainer.setEmail(i + "@" + i + ".com");
            trainer.setIntro("这人个很懒，程威沙雕，程威沙雕，程威沙雕");
            trainer.setTelephone(5168 + "00" + k);
            ts.add(trainer);
        }

        gymRepository.saveAll(Flux.fromIterable(gyms));
        trainerRepository.saveAll(Flux.fromIterable(ts));

        Flux<Gym> gs = gymRepository.findAll().take(10);
        gymRepository.findAll().log();
    }
}
