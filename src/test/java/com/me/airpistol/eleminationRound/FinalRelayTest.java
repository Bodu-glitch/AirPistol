package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;
import com.me.airpistol.athletic.AthleteBuilder;
import com.me.airpistol.athletic.LatestRecord;
import com.me.airpistol.athletic.ShootingAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinalRelayTest {
    FinalRelay finalRelay;
    FinalRelay finalRelay2;

    @BeforeEach
    void setUp() {
        finalRelay = new FinalRelay();
        Athlete athlete1 = new AthleteBuilder("Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 4))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete2 = new AthleteBuilder("Gia", "Tuong")
                .setId(10001)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 6))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete3 = new AthleteBuilder("Gia", "Tuong")
                .setId(10002)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 7))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete4 = new AthleteBuilder("Gia", "Tuong")
                .setId(10003)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 8))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete5 = new AthleteBuilder("Gia", "Tuong")
                .setId(10004)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 9))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete6 = new AthleteBuilder("Gia", "Tuong")
                .setId(10005)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 10))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete7 = new AthleteBuilder("Gia", "Tuong")
                .setId(10006)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 11))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete8 = new AthleteBuilder("Gia", "Tuong")
                .setId(10007)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 12))
                .setShootingAction(new ShootingAction())
                .build();
        for (Athlete athlete : new Athlete[]{athlete1, athlete2, athlete3, athlete4, athlete5, athlete6, athlete7, athlete8}) {
            finalRelay.addAthlete(athlete);
        }

        finalRelay2 = new FinalRelay();
    }

    @AfterEach
    void tearDown() {
        AthleteBuilder.existingIds.clear();
        LatestRecord.existingRecords.clear();
        finalRelay.seriesNumber = 1;
    }

    @Test
    void addAthlete() {
        Relays relays = new Relays();
        Athlete athlete1 = new AthleteBuilder("Gia", "Tuong")
                .setId(10019)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 55))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete2 = new AthleteBuilder("Gia", "Tuong")
                .setId(10020)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 56))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete3 = new AthleteBuilder("Gia", "Tuong")
                .setId(10021)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 58))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete4 = new AthleteBuilder("Gia", "Tuong")
                .setId(10022)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 59))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete5 = new AthleteBuilder("Gia", "Tuong")
                .setId(10023)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 60))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete6 = new AthleteBuilder("Gia", "Tuong")
                .setId(10025)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 61))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete7 = new AthleteBuilder("Gia", "Tuong")
                .setId(10026)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 62))
                .setShootingAction(new ShootingAction())
                .build();
        Athlete athlete8 = new AthleteBuilder("Gia", "Tuong")
                .setId(10044)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 63))
                .setShootingAction(new ShootingAction())
                .build();
        for (Athlete athlete : new Athlete[]{athlete1, athlete2, athlete3, athlete4, athlete5, athlete6, athlete7, athlete8}) {
            relays.addAthlete(athlete);
        }
        relays.addAthlete(new AthleteBuilder("Gia", "Tuong")
                .setId(10090)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 1))
                .setShootingAction(new ShootingAction())
                .build());
        relays.addAthlete(new AthleteBuilder("Gia", "Tuong")
                .setId(10555)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 2))
                .setShootingAction(new ShootingAction())
                .build());
        relays.startRelay();
        relays.endRelay();
        float score = athlete1.getScore();
        for (Athlete athlete : new Athlete[]{athlete1, athlete2, athlete3, athlete4, athlete5, athlete6, athlete7, athlete8}) {
            finalRelay2.addAthlete(athlete);
        }
        assertNotEquals(score, athlete1.getScore());
        assertEquals(0, athlete1.getScore());
        assertEquals(8, finalRelay2.totalAthletes.size());
    }

    @Test
    void testAddAthlete_isFull() {
        assertThrows(RuntimeException.class, () -> {
            finalRelay.addAthlete(new AthleteBuilder("Gia", "Tuong")
                    .setId(10555)
                    .setNationality("Vietnam")
                    .setLatestRecord(new LatestRecord(5, 2))
                    .setShootingAction(new ShootingAction())
                    .build());
        });
    }

    @Test
    void checkIsFull_False() {
        FinalRelay finalRelayTest = new FinalRelay();
        assertFalse(finalRelayTest.checkIsFull());
    }

    @Test
    void cloneList() {
        FinalRelay newfinalRelay = new FinalRelay();
        assertEquals(0, newfinalRelay.totalAthletes.size());
        newfinalRelay.totalAthletes = finalRelay.deepCloneList(finalRelay.totalAthletes);
        assertEquals(8, newfinalRelay.totalAthletes.size());
        finalRelay.totalAthletes.get(0).setInRelay(4);
        assertEquals(4, finalRelay.totalAthletes.get(0).getIsInRelay());
        assertEquals(-1, newfinalRelay.totalAthletes.get(0).getIsInRelay());
    }

    @Test
    void saveRecord() {
        finalRelay.saveRecord(1);
        assertEquals(1, finalRelay.record.size());
    }

    @Test
    void startRound() {
        finalRelay.startRound();
        for (Athlete athlete : finalRelay.totalAthletes) {
            assertNotEquals(0, athlete.getScore());
        }
    }

    @Test
    void startSeries() {
        finalRelay.startSeries();
        assertEquals(1, finalRelay.record.size());
        assertEquals(2, finalRelay.seriesNumber);
    }

    @Test
    void showLeaderBoard() {
        finalRelay.startSeries();
        finalRelay.startSeries();
        finalRelay.showLeaderBoard();
    }

    @Test
    void eliminateAthlete() {
        for (int i = 1; i < 6; i++) {
            finalRelay.startSeries();
        }
        assertEquals(1, finalRelay.eliminatedAthletes.size());
        assertEquals(7, finalRelay.totalAthletes.size());
    }

    @Test
    void showEliminatedAthletes() {
        for (int i = 1; i < 6; i++) {
            finalRelay.startSeries();
        }
        finalRelay.showEliminatedAthletes();
    }

    @Test
    void runFinal_showLastResult() {
        finalRelay.runFinal();
    }

}