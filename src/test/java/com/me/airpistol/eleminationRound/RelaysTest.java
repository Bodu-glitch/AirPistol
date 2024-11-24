package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;
import com.me.airpistol.athletic.AthleteBuilder;
import com.me.airpistol.athletic.LatestRecord;
import com.me.airpistol.athletic.ShootingAction;
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

        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .build();
        relay.addAthlete(newAthlete);
        assertEquals(1, relay.totalAthletes.size());
    }

    @Test
    void addAthlete_AlreadyInRelay() {
        int expected = 1;
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
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
            Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
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
            Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
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
            Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .setLatestRecord(new LatestRecord(i, i))
                    .build();
            relay.addAthlete(newAthlete);
        }
        assertFalse(relay.checkIsFull());
    }

    @Test
    void testEndRelay() {

    }

    @Test
    void testStartRound() {
        relay.totalRound = 60;
        assertThrows(RuntimeException.class, () -> {
            relay.startRound();
        });
    }

    @Test
    void testStartRelay_NotFull() {
        assertThrows(RuntimeException.class, () -> {
            relay.startRelay();
        });
    }

    @Test
    void testStartRelay_isEnded() {
        for (int i = 0; i < 10; i++) {
            int id = 10000 + i;
            Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .setLatestRecord(new LatestRecord(i, i))
                    .setShootingAction(new ShootingAction())
                    .build();
            relay.addAthlete(newAthlete);
        }
        relay.startRelay();
        relay.endRelay();
        assertThrows(RuntimeException.class, () -> {
            relay.startRelay();
        });
    }

    @Test
    void testStartRound_isShoot() {
        relay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(10, 10))
                .build());
        relay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10001)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(5, 5))
                .build());
        relay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10002)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(15, 15))
                .build());
        relay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10003)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(20, 20))
                .build());

        //before start round
        float result1 = relay.totalAthletes.get(0).getScore();
        float result2 = relay.totalAthletes.get(1).getScore();
        float result3 = relay.totalAthletes.get(2).getScore();
        float result4 = relay.totalAthletes.get(3).getScore();

        relay.startRound();

        assertNotEquals(result1, relay.totalAthletes.get(0).getScore());
        assertNotEquals(result2, relay.totalAthletes.get(1).getScore());
        assertNotEquals(result3, relay.totalAthletes.get(2).getScore());
        assertNotEquals(result4, relay.totalAthletes.get(3).getScore());
    }

    @Test
    void testEndRelay_clearAthleteAfterEnd() {
        Athlete athlete1 = new AthleteBuilder("Gia",
                "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(10, 10))
                .build();
        Athlete athlete2 = new AthleteBuilder("Gia",
                "Tuong")
                .setId(10001)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(5, 5))
                .build();
        Athlete athlete3 = new AthleteBuilder("Gia",
                "Tuong")
                .setId(10002)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(15, 15))
                .build();
        Athlete athlete4 = new AthleteBuilder("Gia",
                "Tuong")
                .setId(10003)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(20, 20))
                .build();
        Athlete athlete5 = new AthleteBuilder("Gia",
                "Tuong")
                .setId(10004)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(20, 21))
                .build();
        Athlete athlete6 = new AthleteBuilder("Gia", "Tuong")
                .setId(10005)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(20, 13))
                .build();
        Athlete athlete7 = new AthleteBuilder("Gia", "Tuong")
                .setId(10006)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(20, 43))
                .build();
        Athlete athlete8 = new AthleteBuilder("Gia", "Tuong")
                .setId(10007)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(20, 22))
                .build();
        Athlete athlete9 = new AthleteBuilder("Gia", "Tuong")
                .setId(10008)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(20, 18))
                .build();
        Athlete athlete10 = new AthleteBuilder("Gia", "Tuong")
                .setId(10009)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(20, 29))
                .build();
        for (Athlete athlete : new Athlete[]{athlete1, athlete2, athlete3, athlete4, athlete5, athlete6, athlete7, athlete8, athlete9, athlete10}) {
            relay.addAthlete(athlete);
        }
        relay.startRelay();
        relay.endRelay();
        assertEquals(0, relay.totalAthletes.size());
        assertEquals(-1, athlete1.getIsInRelay());
        assertEquals(-1, athlete2.getIsInRelay());
        assertEquals(-1, athlete3.getIsInRelay());
        assertEquals(-1, athlete4.getIsInRelay());
        assertEquals(-1, athlete5.getIsInRelay());
        assertEquals(-1, athlete6.getIsInRelay());
        assertEquals(-1, athlete7.getIsInRelay());
        assertEquals(-1, athlete8.getIsInRelay());
        assertEquals(-1, athlete9.getIsInRelay());
        assertEquals(-1, athlete10.getIsInRelay());
    }

    @Test
    void testStartRelay_2times() {
        for (int i = 0; i < 10; i++) {
            int id = 10000 + i;
            Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .setLatestRecord(new LatestRecord(i, i))
                    .setShootingAction(new ShootingAction())
                    .build();
            relay.addAthlete(newAthlete);
        }
        relay.startRelay();
        relay.endRelay();
        assertThrows(RuntimeException.class, () -> {
            relay.startRelay();
        });
    }

    @Test
    void testEndRelay_2times() {
        for (int i = 0; i < 10; i++) {
            int id = 10000 + i;
            Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .setLatestRecord(new LatestRecord(i, i))
                    .setShootingAction(new ShootingAction())
                    .build();
            relay.addAthlete(newAthlete);
        }
        relay.startRelay();
        relay.endRelay();
        assertThrows(RuntimeException.class, () -> {
            relay.endRelay();
        });
    }
}