
package com.miage.altea.tp.trainer_api.controller;

import com.miage.altea.tp.trainer_api.bo.Pokemon;
import com.miage.altea.tp.trainer_api.bo.Trainer;
import com.miage.altea.tp.trainer_api.controller.TrainerController;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TrainerController controller;

    @Value("user")
    private String username;

    @Value("Lnw7r1gS8ZbuGaVIHQgA")
    private String password;

    @Test
    void getTrainers_shouldThrowAnUnauthorized(){
        var responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(responseEntity);
        assertEquals(401, responseEntity.getStatusCodeValue());
    }

    @Test
    void getTrainer_withNameAsh_shouldReturnAsh() {
        var ash = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);

        assertNotNull(ash);
        assertEquals("Ash", ash.getName());
        assertEquals(1, ash.getTeam().size());

        assertEquals(25, ash.getTeam().get(0).getPokemonType());
        assertEquals(18, ash.getTeam().get(0).getLevel());
    }

    @Test
    void getAllTrainers_shouldReturnAshAndMisty() {
        var trainers = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/", Trainer[].class);

        Assertions.assertNotNull(trainers);
        assertEquals(3, trainers.length);

        assertEquals("Ash", trainers[0].getName());
        assertEquals("Misty", trainers[1].getName());
    }

    @Test // en dernier
    void postTrainer_shouldReturnAsh() {
        var trainer = new Trainer("mock");
        trainer.setTeam(List.of(new Pokemon(120, 18), new Pokemon(125, 30)));

        var response = this.restTemplate
                .withBasicAuth(username, password)
                .postForEntity("http://localhost:" + port + "/trainers/", trainer, Trainer.class);

        var responseTrainer = response.getBody();
        Assertions.assertNotNull(responseTrainer);
        assertEquals("mock", responseTrainer.getName());
        assertEquals(2, responseTrainer.getTeam().size());

        assertEquals(120, responseTrainer.getTeam().get(0).getPokemonType());
        assertEquals(18, responseTrainer.getTeam().get(0).getLevel());
        assertEquals(200, response.getStatusCode().value());
    }

}