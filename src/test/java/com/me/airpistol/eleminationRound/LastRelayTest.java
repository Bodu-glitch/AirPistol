package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.*;
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
                    .setLatestRecord(new LatestRecord(i, i))
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
                    .setLatestRecord(new LatestRecord(10, 10 + i))
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
                    .setLatestRecord(new LatestRecord(10, 10 + i))
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

    @Test
    void checkIsEndGame() {
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(10, 10))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10001)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(5, 5))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10002)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(15, 15))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10003)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(20, 20))
                .build());
        lastRelay.arrangeAthlete();
        lastRelay.startRelay();
        assertThrows(RuntimeException.class, () -> {
            lastRelay.startRelay();
        });
    }

    @Test
    void checkShootingOrder() {
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(10, 10))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10001)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(5, 5))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10002)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(15, 15))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10003)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(20, 20))
                .build());
        lastRelay.startRelay();
        Athlete resultAthlete1 = lastRelay.totalAthletes.stream().filter(athlete -> athlete.getId() == 10001).findFirst().orElse(null);
        Athlete resultAthlete2 = lastRelay.totalAthletes.stream().filter(athlete -> athlete.getId() == 10000).findFirst().orElse(null);
        Athlete resultAthlete3 = lastRelay.totalAthletes.stream().filter(athlete -> athlete.getId() == 10002).findFirst().orElse(null);
        Athlete resultAthlete4 = lastRelay.totalAthletes.stream().filter(athlete -> athlete.getId() == 10003).findFirst().orElse(null);

        if (resultAthlete1 == null || resultAthlete2 == null || resultAthlete3 == null || resultAthlete4 == null) {
            fail();
        }
        assertEquals(lastRelay.records.get(0), resultAthlete1.getScore());
        assertEquals(lastRelay.records.get(1), resultAthlete2.getScore());
        assertEquals(lastRelay.records.get(2), resultAthlete3.getScore());
        assertEquals(lastRelay.records.get(3), resultAthlete4.getScore());
    }

    @Test
    void testStartRelay_2times() {
        for (int i = 0; i < 4; i++) {
            int id = 10000 + i;
            lastRelay.addAthlete(new AthleteBuilder("Gia", "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .setLatestRecord(new LatestRecord(i, i))
                    .setShootingAction(new ShootingAction())
                    .build());
        }
        lastRelay.startRelay();
        lastRelay.endRelay();
        assertThrows(RuntimeException.class, () -> {
            lastRelay.startRelay();
        });
    }

    @Test
    void testEndRelay() {
        lastRelay.totalRound = 60;
        assertThrows(RuntimeException.class, () -> {
            lastRelay.endRelay();
        });
    }

    @Test
    void testStartRound() {
        lastRelay.totalRound = 60;
        assertThrows(RuntimeException.class, () -> {
            lastRelay.startRound();
        });
    }

    @Test
    void testStartRound_isShoot() {
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(10, 10))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10001)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(5, 5))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10002)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(15, 15))
                .build());
        lastRelay.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10003)
                .setNationality("Vietnam")
                .setShootingAction(new ShootingAction())
                .setLatestRecord(new LatestRecord(20, 20))
                .build());

        //before start round
        float result1 = lastRelay.totalAthletes.get(0).getScore();
        float result2 = lastRelay.totalAthletes.get(1).getScore();
        float result3 = lastRelay.totalAthletes.get(2).getScore();
        float result4 = lastRelay.totalAthletes.get(3).getScore();

        lastRelay.startRound();

        assertNotEquals(result1, lastRelay.totalAthletes.get(0).getScore());
        assertNotEquals(result2, lastRelay.totalAthletes.get(1).getScore());
        assertNotEquals(result3, lastRelay.totalAthletes.get(2).getScore());
        assertNotEquals(result4, lastRelay.totalAthletes.get(3).getScore());
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
        for (Athlete athlete : new Athlete[]{athlete1, athlete2, athlete3, athlete4}) {
            lastRelay.addAthlete(athlete);
        }
        lastRelay.startRelay();
        lastRelay.endRelay();
        assertEquals(0, lastRelay.totalAthletes.size());
        assertEquals(-1, athlete1.getIsInRelay());
        assertEquals(-1, athlete2.getIsInRelay());
        assertEquals(-1, athlete3.getIsInRelay());
        assertEquals(-1, athlete4.getIsInRelay());
    }
}