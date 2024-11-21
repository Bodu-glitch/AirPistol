package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;
import com.me.airpistol.athletic.AthleteBuilder;
import com.me.airpistol.athletic.LatestRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelaysTest {
    Relays relay;

    @BeforeEach
    void setUp() {
        relay = new Relays();
    }

    @AfterEach
    void tearDown() {
        relay.totalAthletes.clear();
        AthleteBuilder.existingIds.clear();
        LatestRecord.existingRecords.clear();
    }

    @Test
    void addAthlete() {

        Athlete newAthlete = new AthleteBuilder( "Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .build();
        relay.addAthlete(newAthlete);
        assertEquals(1, relay.totalAthletes.size());
    }

    @Test
    void addAthlete_AlreadyInRelay() {
        int expected = 1;
        Athlete newAthlete = new AthleteBuilder( "Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(10, 10))
                .build();
        relay.addAthlete(newAthlete);
        relay.addAthlete(newAthlete);
        assertEquals(expected, relay.totalAthletes.size());
    }

    @Test
    void addAthlete_OutOfRange() {
        int expected = 10;
        for (int i = 0; i < 11; i++) {
            int id = 10000 + i;
            Athlete newAthlete = new AthleteBuilder( "Gia", "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .setLatestRecord(new LatestRecord(i, i))
                    .build();
            relay.addAthlete(newAthlete);
        }

        int actual = relay.totalAthletes.size();

        assertEquals(expected, actual);
    }

    @Test
    void checkIsFull() {

        for (int i = 0; i < 10; i++) {
            int id = 10000 + i;
            Athlete newAthlete = new AthleteBuilder( "Gia", "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .setLatestRecord(new LatestRecord(i, i))
                    .build();
            relay.addAthlete(newAthlete);
        }

        assertTrue(relay.checkIsFull());
    }

    @Test
    void checkIsFull_False() {

        for (int i = 0; i < 9; i++) {
            int id = 10000 + i;
            Athlete newAthlete = new AthleteBuilder( "Gia", "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .setLatestRecord(new LatestRecord(i, i))
                    .build();
            relay.addAthlete(newAthlete);
        }
        assertFalse(relay.checkIsFull());
    }
}