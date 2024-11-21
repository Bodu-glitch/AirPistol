package com.me.airpistol.athletic;

import javax.swing.text.html.parser.Parser;
import java.util.ArrayList;
import java.util.List;

public class AthleteBuilder {
    int id;
    String firstName;
    String lastName;
    ShootingStrategy shootingAction;
    String nationality;
    LatestRecord latestRecord;
    int isInRelay;
    public static List<Integer> existingIds = new ArrayList<>();

    boolean canCreate = true;

    public AthleteBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AthleteBuilder() {
        existingIds = new ArrayList<>();
    }

    // 00001 - 99999 ?? 10000 - 99999
    public AthleteBuilder setId(int id) {
        if (id < 10000 || id > 99999) {
            System.err.println("Invalid ID");
            canCreate = false;
        } else if (existingIds.contains(id)) {
            System.err.println("ID already exists");
            canCreate = false;
        }
        this.id = id;
        return this;
    }

    public AthleteBuilder setShootingAction(ShootingStrategy shootingAction) {
        this.shootingAction = shootingAction;
        return this;
    }

    public AthleteBuilder setInRelay(int isInRelay) {
        this.isInRelay = isInRelay;
        return this;
    }

    public AthleteBuilder setNationality(String nationality) {
        String[] nationalityArray = nationality.split(",");
        if (nationalityArray.length == 1) {
            if (nationality.equals("Russia") || nationality.equals("Belarus")) {
                System.err.println("Athlete cannot represent this country, it must be neutral");
                canCreate = false;
            }
            this.nationality = nationality;
        } else {
            System.err.println("Athlete cannot represent multiple countries");
            canCreate = false;
        }
        return this;
    }

    public AthleteBuilder setLatestRecord(LatestRecord latestRecord) {
        if (latestRecord.rankRecord < 0 || latestRecord.scoreRecord < 0) {
            System.err.println("Invalid record");
            canCreate = false;
        }

        if (latestRecord.rankRecord > 64 || latestRecord.scoreRecord > 10.9f * 60) {
            System.err.println("Invalid record");
            canCreate = false;
        }

        for (LatestRecord record : LatestRecord.existingRecords) {
            if (record.rankRecord == latestRecord.rankRecord) {
                System.err.println("Rank already exists");
                canCreate = false;
                break;
            }
        }

        this.latestRecord = latestRecord;
        LatestRecord.existingRecords.add(latestRecord);
        return this;
    }

    public Athlete build() {
        if (!canCreate) {
            return null;
        }
        if (this.id == 0) {
            System.err.println("Athlete must be assigned an ID");
            return null;
        }
        if (this.nationality == null) {
            System.err.println("Athlete must be assigned a nationality");
            return null;
        }
        if (this.latestRecord == null) {
            System.err.println("Athlete must have a record");
            return null;
        }

        existingIds.add(id);
        this.isInRelay = -1;
        return new Athlete(this);
    }
}

