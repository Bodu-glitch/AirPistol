package com.me.airpistol.athletic;

import java.util.ArrayList;
import java.util.List;

public class AthleteManager {
    List<Athlete> athletes = new ArrayList<>();

    public void addAthlete(Athlete newAthlete) {
        if (newAthlete == null) {
            throw new IllegalArgumentException("Athlete cannot be null");
        }
        if (this.athletes.size() == 64) {
            System.out.println("This competition is full");
            return;
        }
        this.athletes.add(newAthlete);
    }

    public void removeAthlete(int id) {
        if (this.athletes.removeIf(athlete -> athlete.id == id)) {
            AthleteBuilder.existingIds.removeIf(Id -> Id == id);
        } else {
            System.out.println("Athlete not found");
        }
    }

//    public void printAthletes() {
//        System.out.printf("%-20s %-20s %-20s %-10s %-10s %-10s%n", "First Name", "Last Name", "Nationality", "ID", "Rank", "Score");
//        System.out.println("------------------------------------------------------------------------------------------");
//        for (Athlete athlete : athletes) {
//            System.out.printf("%-20s %-20s %-20s %-10d %-10d %-10.2f%n",
//                    athlete.firstName,
//                    athlete.lastName,
//                    athlete.nationality,
//                    athlete.id,
//                    athlete.getLatestRecord().getRankRecord(),
//                    athlete.getLatestRecord().scoreRecord);
//        }
//    }
//
//    public void printResultOfRound() {
//        for (Athlete athlete : athletes) {
//            System.out.println(athlete.toString());
//        }
//    }

    public ArrayList<Athlete> getAthleteGotoNextRound() {
        athletes.sort((a1, a2) -> Float.compare(a2.shootingAction.getShootingScore(), a1.shootingAction.getShootingScore()));
        ArrayList<Athlete> athletesGotoNextRound = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            athletesGotoNextRound.add(athletes.get(i));
        }
        return athletesGotoNextRound;
    }


}
