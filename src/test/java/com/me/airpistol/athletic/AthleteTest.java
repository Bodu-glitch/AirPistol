package com.me.airpistol.athletic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AthleteTest {

    @Test
    void testClone() {
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 4))
                .setShootingAction(new ShootingAction())
                .build();
    }
}