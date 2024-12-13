package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;

import java.util.*;

public class FinalRelay implements FinalRelayInterface, RelayInterface {
    List<Athlete> totalAthletes = new ArrayList<>();
    Map<Integer, List<Athlete>> record = new HashMap<>();
    int seriesNumber = 1;
    List<Athlete> eliminatedAthletes = new ArrayList<>();

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
    public void startRound() {
        Random random = new Random();
        for (Athlete athlete : this.totalAthletes) {
            athlete.shoot(random.nextFloat(0.1F, 10.0F));
            athlete.shootingAction.setCurrentShots(0);
        }
    }

    @Override
    public void startSeries() {
        for (int i = 0; i < 2; i++) {
            this.startRound();
        }
        this.saveRecord(this.seriesNumber);
        this.seriesNumber += 1;
        this.eliminateAthlete();
    }

    @Override
    public void showEliminatedAthletes() {
        for (Athlete athlete : eliminatedAthletes) {
            System.out.println("Athlete " + athlete.getId() + " is eliminated");
        }
    }

    @Override
    public void saveRecord(int roundNumber) {
        this.record.put(roundNumber, deepCloneList(totalAthletes));
    }

    public List<Athlete> deepCloneList(List<Athlete> original) {
        List<Athlete> clone = new ArrayList<>();
        for (Athlete athlete : original) {
            clone.add(athlete.clone());
        }
        return clone;
    }

    @Override
    public void showLeaderBoard() {
        List<Athlete> leaderBoard = new ArrayList<>(record.get(record.size()));
        leaderBoard.sort((a1, a2) -> Float.compare(a2.getScore(), a1.getScore()));
        for (Athlete athlete : leaderBoard) {
            System.out.println(athlete.getId() + " " + athlete.getScore());
        }
    }

    @Override
    public void eliminateAthlete() {
        if (this.seriesNumber % 5 == 0) {
            List<Athlete> leaderBoard = new ArrayList<>(record.get(record.size()));
            leaderBoard.sort((a1, a2) -> Float.compare(a2.getScore(), a1.getScore()));
            int id = leaderBoard.get(leaderBoard.size() - 1).getId();
            for (Iterator<Athlete> iterator = totalAthletes.iterator(); iterator.hasNext(); ) {
                Athlete athlete = iterator.next();
                if (athlete.getId() == id) {
                    iterator.remove();
                    eliminatedAthletes.add(athlete);
                    return;
                }
            }
        }
    }

    @Override
    public void runFinal() {
        while (totalAthletes.size() > 1) {
            this.startSeries();
        }
        this.showLastResult();
    }

    public void showLastResult() {
        List<Athlete> lastResult = new ArrayList<>(record.get(record.size()));
        eliminatedAthletes.remove(eliminatedAthletes.size() - 1);
        lastResult.addAll(eliminatedAthletes);
        lastResult.sort((a1, a2) -> Float.compare(a2.getScore(), a1.getScore()));
        for (Athlete athlete : lastResult) {
            System.out.println(athlete.getId() + " " + athlete.getScore());
        }
    }

}
