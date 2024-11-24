package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;

import java.util.List;

public class FinalRelay implements FinalRelayInterce, RelayInterface {

    List<Athlete> totalAthletes;

    @Override
    public void addAthlete(Athlete newAthlete) {
        if (this.checkIsFull()) {
            throw new RuntimeException("Just 8 athletes are allowed");
        }
        newAthlete.setDefaultShootingAction();
        totalAthletes.add(newAthlete);
    }

    @Override
    public boolean checkIsFull() {
        if (totalAthletes.size() == 8) {
            return true;
        }
        return false;
    }

    @Override
    public void startRelay() {

    }

    @Override
    public void endRelay() {

    }

    @Override
    public void startRound() {

    }

    @Override
    public void endRound() {

    }
}
