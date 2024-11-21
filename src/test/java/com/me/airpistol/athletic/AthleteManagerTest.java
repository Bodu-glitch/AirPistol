package com.me.airpistol.athletic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AthleteManagerTest {

    AthleteManager manager;

    @BeforeEach
    void setUp() {
        manager = new AthleteManager();
    }

    @AfterEach
    void tearDown() {
        manager.athletes.clear();
        AthleteBuilder.existingIds.clear();
        LatestRecord.existingRecords.clear();
    }

    @Test
    void addAthlete_Success() {
        int expected = 1;
        manager.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(10, 10))
                .build());
        int actual = manager.athletes.size();
        assertEquals(expected, actual);
    }

    @Test
    void addAthlete_Full() {
        int expected = 64;
        for (int i = 0; i < 65; i++) {
            int id = 10000 + i;
            manager.addAthlete(new AthleteBuilder("Gia",
                    "Tuong")
                    .setId(id)
                    .setNationality("Vietnam")
                    .setLatestRecord(new LatestRecord(i, i))
                    .build());
        }
        int actual = manager.athletes.size();
        assertEquals(expected, actual);
    }

    @Test
    void removeAthlete_Success() {
        int expected = 0;
        manager.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(10, 10))
                .build());
        manager.removeAthlete(10000);
        assertFalse(AthleteBuilder.existingIds.stream().anyMatch(id -> id == 10000));
        assertEquals(expected, manager.athletes.size());
    }

    @Test
    void removeAthlete_NotFound() {
        int expected = 1;
        manager.addAthlete(new AthleteBuilder("Gia",
                "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(10, 10))
                .build());
        manager.removeAthlete(10001);
        assertTrue(AthleteBuilder.existingIds.stream().anyMatch(id -> id == 10000));
        assertEquals(expected, manager.athletes.size());
    }

    @Test
    void getAthleteGotoNextRound() {
        for (int i = 0 ; i < 64 ; i++){
            manager.addAthlete(new AthleteBuilder("Gia",
                    "Tuong")
                    .setId(10000 + i)
                    .setNationality("Vietnam")
                    .setShootingAction(new ShootingAction())
                    .setLatestRecord(new LatestRecord(i, i))
                    .build());
        }

        for (int i = 0 ; i < 60 ; i++) {
            for (int j = 0; j < 64; j++) {
                if (j < 8) {
                    manager.athletes.get(j).shootingAction.shoot(0.1f + 0.575f * j); // 0 - 0.575 bằng nhau
                } else if (j < 16) {
                    manager.athletes.get(j).shootingAction.shoot( (0.8f * j) / 10 + 5.75f);
                } else if (j < 24) {
                    manager.athletes.get(j).shootingAction.shoot( (0.8f * j) / 10 + 13.75f);
                } else if (j < 32) {
                    manager.athletes.get(j).shootingAction.shoot(  (0.8f * j) / 10 + 21.75f);
                } else if (j < 40) {
                    manager.athletes.get(j).shootingAction.shoot(  (0.8f * j) / 10 + 30);
                } else if (j < 48) {
                    manager.athletes.get(j).shootingAction.shoot( (0.8f * j) / 10 + 38.75f);
                }
            }
        }

        assertEquals(8, manager.getAthleteGotoNextRound().size());
        assertEquals(654.0f, manager.getAthleteGotoNextRound().get(0).shootingAction.getShootingScore());
        assertEquals(648.0f, manager.getAthleteGotoNextRound().get(1).shootingAction.getShootingScore());
        assertEquals(642.0f, manager.getAthleteGotoNextRound().get(2).shootingAction.getShootingScore());
        assertEquals(636.0f, manager.getAthleteGotoNextRound().get(3).shootingAction.getShootingScore());
        assertEquals(630.0f, manager.getAthleteGotoNextRound().get(4).shootingAction.getShootingScore());
        assertEquals(624.0f, manager.getAthleteGotoNextRound().get(5).shootingAction.getShootingScore());
        assertEquals(618.0f, manager.getAthleteGotoNextRound().get(6).shootingAction.getShootingScore());
        assertEquals(612.0f, manager.getAthleteGotoNextRound().get(7).shootingAction.getShootingScore());
    }
}