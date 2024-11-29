package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LastRelay extends RelayAbstract {
    int totalRound;
    List<Float> records = new ArrayList<>();
    Boolean isRelayEnd = false;

    public LastRelay(int relayNumber) {
        super.relayNumber = relayNumber;
    }

    public LastRelay() {

    }

    @Override
    public void addAthlete(Athlete newAthlete) {
        if (super.totalAthletes.size() > 3) {
            System.out.println("Relay is full");
            return;
        }
        super.totalAthletes.add(newAthlete);
        this.arrangeAthlete();
    }

    @Override
    public boolean checkIsFull() {
        return super.totalAthletes.size() == 4;
    }

    public void arrangeAthlete() {
        totalAthletes.sort((a1, a2) -> a1.getLatestRecord().getRankRecord() - a2.getLatestRecord().getRankRecord());
    }


    @Override
    public void startRelay() {
        if (this.isRelayEnd) {
            throw new RuntimeException("Relay has ended");
        }
        for (int i = 0; i < 60; i++) {
            this.startRound();
            this.totalRound += 1;

            //check order shooting score
            if (this.totalRound == 59) {
                for (Athlete athlete : this.totalAthletes) {
                    this.records.add(athlete.getScore());
                }
                return;
            }
        }
    }

    public void startRound() {
        if (this.totalRound >= 60) {
            throw new RuntimeException("Total round has reached 60");
        }
        Random random = new Random();
        for (Athlete athlete : this.totalAthletes) {
            athlete.shoot(random.nextFloat(0.1F, 10.0F));
        }
    }

    @Override
    public void endRelay() {
        if (this.totalRound > 59) {
            throw new RuntimeException("Relay has ended");
        }
        this.isRelayEnd = true;
        for (Athlete athlete : this.totalAthletes) {
            athlete.setInRelay(-1);
        }
        this.totalAthletes.clear();
        System.out.println("Relay has ended");
    }

}
