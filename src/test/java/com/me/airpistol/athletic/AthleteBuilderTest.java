/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.me.airpistol.athletic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author PC
 */
public class AthleteBuilderTest {
    AthleteBuilder builder;

    public AthleteBuilderTest() {
        builder = new AthleteBuilder();
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterEach
    void tearDown() {
        LatestRecord.existingRecords.clear();
        AthleteBuilder.existingIds.clear();
    }

    @Test
    public void testSetId() {
        int expected = 10000;
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(expected)
                .setNationality("Vietnam")
                .setLatestRecord(new LatestRecord(10, 10)).build();
        assertEquals(expected, newAthlete.id);
    }

    @Test
    public void testSetId_Invalid() {
        int id = 102;
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong").setId(id).setNationality("Vietnam").build();
        assertNull(newAthlete);
    }

    @Test
    public void testSetId_ExistedId() {
        int id = 10000;
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong").setId(id).setNationality("France").setLatestRecord(new LatestRecord(10, 2)).build();
        Athlete newAthlete2 = new AthleteBuilder("Hong", "Lai").setId(id).setNationality("Poland").setLatestRecord(new LatestRecord(222.1f, 1)).build();
        assertNull(newAthlete2);
    }

    @Test
    public void testSetNationality() {
        String expected = "Vietnam";
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(10245)
                .setLatestRecord(new LatestRecord(10, 10))
                .setNationality(expected).build();
        assertEquals(expected, newAthlete.nationality);
    }

    @Test
    public void testSetNationality_Invalid() {
        String nationality = "Vietnam,Australia";
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong").setNationality(nationality).build();
        assertNull(newAthlete);
    }

    @Test
    public void testSetNationality_InvalidCountry() {
        String nationality = "Russia";
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong").setNationality(nationality).build();
        assertNull(newAthlete);
    }

    @Test
    public void testSetLatestRecord() {
        LatestRecord expected = new LatestRecord(10, 10);
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong")
                .setId(10245)
                .setNationality("Vietnam")
                .setLatestRecord(expected).build();
        assertEquals(expected, newAthlete.latestRecord);
    }

    @Test
    public void testSetLatestRecord_NegativeInputScore() {
        LatestRecord latestRecord = new LatestRecord(-1, 1);
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong").setLatestRecord(latestRecord).build();
        assertNull(newAthlete);
    }

    @Test
    public void testSetLatestRecord_NegativeInputRank() {
        LatestRecord latestRecord = new LatestRecord(1, -10);
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong").setLatestRecord(latestRecord).build();
        assertNull(newAthlete);
    }

    @Test
    public void testBuild_IdNotNull() {
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong").build();
        assertNull(newAthlete);
    }

    @Test
    public void testSetLatestRecord_RankOutOfRange() {
        LatestRecord latestRecord = new LatestRecord(1, 65);
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong").setLatestRecord(latestRecord).build();
        assertNull(newAthlete);
    }

    @Test
    void testSetLatestRecord_ExistingRank() {
        LatestRecord latestRecord = new LatestRecord(1, 1);
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong").setLatestRecord(latestRecord).build();
        Athlete newAthlete2 = new AthleteBuilder("Hong", "Lai").setLatestRecord(latestRecord).build();
        assertNull(newAthlete2);
    }


    @Test
    public void testBuild_NationalityNotNull() {
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong").setId(10245).build();
        assertNull(newAthlete);
    }

    @Test
    void testBuild_LatestRecordNotNull() {
        Athlete newAthlete = new AthleteBuilder("Gia", "Tuong").setId(10245).setNationality("Vietnam").build();
        assertNull(newAthlete);
    }

}
