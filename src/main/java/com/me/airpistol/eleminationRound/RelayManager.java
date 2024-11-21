package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;

import java.util.*;

public class RelayManager {
    List<RelayAbstract> relays = new ArrayList<>();
    List<RelayAbstract> records;

    public void printRelayRecord (){

    }

    public boolean addRelay() {
        int relayNumber = relays.size() + 1;

        if (relays.size() < 5) {
            relays.add(new Relays(relayNumber));
            System.out.println("Relay added");
            return true;
        }else if (relays.size() == 5){
            relays.add(new LastRelay(relayNumber));
            System.out.println("Last relay added");
            return true;
        }else {
            System.err.println("Relay is full");
            return false;
        }
    }

    public void removeRelay(int relayNumber) {
        for (Iterator<RelayAbstract> iterator = relays.iterator(); iterator.hasNext();) {
            RelayAbstract relay = iterator.next();
            if (relay.relayNumber == relayNumber) {
                for (Athlete athlete : relay.totalAthletes) {
                    athlete.setInRelay(relayNumber);
                }

                iterator.remove();
                return;
            }
        }
        System.err.println("Relay not found");
    }

    // không dùng for được vì đang chỉnh sửa list
    public void addAthleteToRelay(Athlete athlete) {
        if (athlete == null) {
            System.err.println("Athlete cannot be null");
            return;
        }
        if (athlete.getIsInRelay() != -1) {
            System.err.println("Athlete is in relay");
            return;
        }
        boolean added = false;
        for (Iterator<RelayAbstract> iterator = relays.iterator(); iterator.hasNext();) {
            RelayInterface relay = iterator.next();
            System.out.println(relay.getClass());
            if (!relay.checkIsFull()) {
                relay.addAthlete(athlete);
                athlete.setInRelay(-1);
                added = true;
                break;
            }
        }
        if (!added) {
            if (this.relays.size() != 6) {
                this.addRelay();
                System.out.println("New relay added");
                if (relays.get(relays.size() - 1).getClass() == LastRelay.class) {
                    System.out.println("Athlete last relay added");
                }
                relays.get(relays.size() - 1).addAthlete(athlete);
                athlete.setInRelay(-1);
            } else {
                System.err.println("All relays are full");
            }
        }
    }


    public void addAAthleteToRelay(Athlete athlete, int relayNumber) {
        if (athlete == null) {
            System.err.println("Athlete cannot be null");
            return;
        }
        if (athlete.getIsInRelay() != -1) {
            System.err.println("Athlete is in relay");
            return;
        }

        for (Iterator<RelayAbstract> iterator = relays.iterator(); iterator.hasNext();) {
            RelayAbstract relay = iterator.next();
            if (relay.relayNumber == relayNumber) {
                if (relay.checkIsFull()) {
                    System.err.println("Relay is full");
                    return;
                }
                relay.addAthlete(athlete);
                athlete.setInRelay(relayNumber);
                return;
            }
        }
        System.err.println("Relay not found");
    }

    public void saveRecord(RelayAbstract record) {
        records.add(record);
    }

}
