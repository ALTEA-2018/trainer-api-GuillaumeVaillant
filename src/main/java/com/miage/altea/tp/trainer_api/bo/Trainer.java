package com.miage.altea.tp.trainer_api.bo;

import javax.persistence.*;
import java.util.List;

@Entity // declaration objet database
public class Trainer {

    @Id // clée primaire
    private String name;

    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Pokemon> team;

    public Trainer() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }
}