package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinalRelay implements FinalRelayInterce, RelayInterface {

    List<Athlete> totalAthletes = new ArrayList<>();
    Map<List<Athlete>, Integer> record = new HashMap<>();

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

    @Override
    public void showEliminatedAthletes() {

    }

    @Override
    public void saveRecord(int roundNumber) {

    }

    public static List<Athlete> deepCloneList(List<Athlete> original) {
        List<Athlete> clone = new ArrayList<>();
        for (Athlete athlete : original) {
            clone.add(athlete.clone());
        }
        return clone;
    }

    @Override
    public void showLeaderBoard() {

    }
}
