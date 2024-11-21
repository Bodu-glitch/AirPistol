package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;
import com.me.airpistol.athletic.AthleteBuilder;
import com.me.airpistol.athletic.LatestRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LastRelayTest {

    LastRelay lastRelay;

    @BeforeEach
    void setUp() {
        lastRelay = new LastRelay();
    }

    @AfterEach
    void tearDown() {
        lastRelay.totalAthletes.clear();
        AthleteBuilder.existingIds.clear();
        LatestRecord.existingRecords.clear();
    }

    @Test
    void addAthlete() {
        int expected = 1;
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .build());
        int actual = lastRelay.totalAthletes.size();
        assertEquals(expected, actual);
    }

    @Test
    void addAthlete_OutOfRange() {
        int expected = 4;
        for (int i = 0; i < 5; i++) {
            int id = 10000 + i;
            lastRelay.addAthlete(new AthleteBuilder("Gia",
                    "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .build());
        }
        int actual = lastRelay.totalAthletes.size();
        assertEquals(expected, actual);
    }

    @Test
    void checkIsFull() {
        for (int i = 0; i < 4; i++) {
            int id = 10000 + i;
            lastRelay.addAthlete(new AthleteBuilder("Gia",
                    "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .build());
        }
        assertTrue(lastRelay.checkIsFull());
    }

    @Test
    void checkIsFull_False() {
        for (int i = 0; i < 3; i++) {
            int id = 10000 + i;
            lastRelay.addAthlete(new AthleteBuilder("Gia",
                    "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .build());
        }
        assertFalse(lastRelay.checkIsFull());
    }

    @Test
    public void arrangeAthlete() {
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(10, 10))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10001)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 5))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10002)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(15, 15))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10003)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(20, 20))
                .build());
        lastRelay.arrangeAthlete();
        assertEquals(5, lastRelay.totalAthletes.get(0).getLatestRecord().getRankRecord());
        assertEquals(10, lastRelay.totalAthletes.get(1).getLatestRecord().getRankRecord());
        assertEquals(15, lastRelay.totalAthletes.get(2).getLatestRecord().getRankRecord());
        assertEquals(20, lastRelay.totalAthletes.get(3).getLatestRecord().getRankRecord());
    }
}