package com.me.airpistol.athletic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AthleteTest {

    @AfterEach
    void tearDown() {
        AthleteBuilder.existingIds.clear();
        LatestRecord.existingRecords.clear();
    }

    @Test
    void testClone() {
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 4))
                .setShootingAction(new ShootingAction())
                .build();

        Athlete clonedAthlete = newAthlete.clone();
        newAthlete.shoot(4);
        assertEquals(0, clonedAthlete.getScore());
    }

    @Test
    void testGetScore() {
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 4))
                .setShootingAction(new ShootingAction())
                .build();
        assertEquals(0, newAthlete.getScore());
    }

    @Test
    void testSetInRelay() {
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 4))
                .setShootingAction(new ShootingAction())
                .build();
        newAthlete.setInRelay(4);
        assertEquals(4, newAthlete.getIsInRelay());
    }

    @Test
    void testShoot() {
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 4))
                .setShootingAction(new ShootingAction())
                .build();
        newAthlete.shoot(5);
        assertNotEquals(0, newAthlete.getScore());
    }

    @Test
    void testSetDefaultShootingAction() {
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 4))
                .setShootingAction(new ShootingAction())
                .build();
        newAthlete.shoot(5);
        newAthlete.setDefaultShootingAction();
        assertEquals(0, newAthlete.getScore());
    }

    @Test
    void testGetFullName() {
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 4))
                .setShootingAction(new ShootingAction())
                .build();
        assertEquals("Gia Tuong", newAthlete.getFullName());
    }

    @Test
    void testToString() {
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(10000)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(5, 4))
                .setShootingAction(new ShootingAction())
                .build();
        String data = newAthlete.toString();
        System.out.println(data);
    }
    
}