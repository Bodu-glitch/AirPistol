package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Relays extends RelayAbstract {

    int totalRound = 0;
    boolean isEnded = false;

    public Relays(int relayNumber) {
        super();
        this.relayNumber = relayNumber;
    }

    public Relays() {

    }


    @Override
    public void addAthlete(Athlete newAthlete) {
        if (this.totalAthletes.size() == 10) {
            System.out.println("Relay is full");
            return;
        } else if (totalAthletes.stream().anyMatch(athlete -> athlete.getId() == newAthlete.getId())) {
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
        if (this.isEnded) {
            throw new RuntimeException("Relay has ended");
        }
        if (this.totalAthletes.size() != 10) {
            throw new RuntimeException("Relay is not full");
        }
        for (int i = 0; i < 60; i++) {
            this.startRound();
            this.totalRound += 1;
        }
    }

    public void startRound() {
        if (this.totalRound > 59) {
            throw new RuntimeException("Relay has ended");
        }
        Random random = new Random();
        for (Athlete athlete : this.totalAthletes) {
            athlete.shoot(random.nextFloat(0, 10));
        }
    }

    @Override
    public void endRelay() {
        if (this.isEnded) {
            throw new RuntimeException("Relay has ended");
        }
        for (Athlete athlete : this.totalAthletes) {
            athlete.setInRelay(-1);
        }
        this.totalAthletes.clear();
        this.isEnded = true;
    }

}
