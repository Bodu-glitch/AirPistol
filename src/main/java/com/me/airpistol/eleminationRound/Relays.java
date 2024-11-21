package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;

import java.util.ArrayList;
import java.util.List;

public class Relays extends RelayAbstract {

    int totalRound = 60;

    public Relays(int relayNumber) {
        super();
        this.relayNumber = relayNumber;
    }

    public Relays(){

    }


    @Override
    public void addAthlete(Athlete newAthlete) {
        if (this.totalAthletes.size() == 10){
            System.out.println("Relay is full");
            return;
        }else if (totalAthletes.stream().anyMatch(athlete -> athlete.getId() == newAthlete.getId())){
            System.out.println("Athlete already in relay");
            return;
        }
        this.totalAthletes.add(newAthlete);
    }

    @Override
    public boolean checkIsFull() {
        return this.totalAthletes.size() == 10;
    }

    @Override
    public void startRelay() {

    }

    @Override
    public void endRelay() {

    }

}
