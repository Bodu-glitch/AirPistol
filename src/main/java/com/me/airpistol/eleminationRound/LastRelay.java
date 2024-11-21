package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;

import java.util.ArrayList;
import java.util.List;

public class LastRelay extends RelayAbstract {
    int totalRound;

    public LastRelay(int relayNumber) {
        super.relayNumber = relayNumber;
    }

    public LastRelay(){

    }

    @Override
    public void addAthlete(Athlete newAthlete) {
        if (super.totalAthletes.size() > 3) {
            System.out.println("Relay is full");
            return;
        }
        super.totalAthletes.add(newAthlete);
    }

    @Override
    public boolean checkIsFull() {
        return super.totalAthletes.size() == 4;
    }

    public void arrangeAthlete(){
        totalAthletes.sort((a1, a2) -> a1.getLatestRecord().getRankRecord() - a2.getLatestRecord().getRankRecord());
    }


    @Override
    public void startRelay() {
        System.out.println("startRelay");
    }

    @Override
    public void endRelay() {
        System.out.println("endRelay");
    }

}
