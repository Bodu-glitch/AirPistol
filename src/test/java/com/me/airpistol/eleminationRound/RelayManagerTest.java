package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;
import com.me.airpistol.athletic.AthleteBuilder;
import com.me.airpistol.athletic.LatestRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RelayManagerTest {

    RelayManager relays;

    @BeforeEach
    void setUp() {
        relays = new RelayManager();
    }

    @AfterEach
    void tearDown() {
        relays.relays.clear();
        AthleteBuilder.existingIds.clear();
        LatestRecord.existingRecords.clear();
    }

    @Test
    void addRelay() {
        relays.addRelay();
        relays.addRelay();
        assertEquals(2, relays.relays.size());
    }

    @Test
    void addRelay_OutOfRange() {
        for (int i = 0; i < 7; i++) {
            relays.addRelay();
        }
        assertEquals(6, relays.relays.size());
    }

    @Test
    void removeRelay_NotFound() {
        relays.addRelay();
        relays.removeRelay(2);
        assertEquals(1, relays.relays.size());
    }

    @Test
    void removeRelay() {
        relays.addRelay();
        relays.addRelay();
        relays.addRelay();
        relays.addRelay();
        relays.addRelay();
        relays.addRelay();

        relays.removeRelay(1);
        assertEquals(5, relays.relays.size());
    }

    @Test
    void removeRelay_HaveAthlete() {
        relays.addRelay();
        Athlete newAthlete = new AthleteBuilder("Gia","Tuong").setId(10000).setNationality("Vietnam").setLatestRecord(new LatestRecord(10.10F,10)).build();
        relays.addAAthleteToRelay(newAthlete, 1);
        relays.removeRelay(1);
        assertEquals(0, relays.relays.size());
//        assertFalse(newAthlete.getIsInRelay());
    }

    @Test
    void addAthleteToRelay() {
        for (int i = 0; i < 54; i++) {
            Athlete newAthlete = new AthleteBuilder("Gia","Tuong").setId(10000 + i).setNationality("Vietnam").setLatestRecord(new LatestRecord(i,i)).build();
//            assertFalse(newAthlete.getIsInRelay());
            relays.addAthleteToRelay(newAthlete);
//            assertTrue(newAthlete.getIsInRelay());
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(10, relays.relays.get(i).totalAthletes.size());
        }

        assertEquals(4, relays.relays.get(5).totalAthletes.size());
        assertEquals(6, relays.relays.size());
    }

    @Test
    void addAthleteToRelay_NullAthlete(){
        relays.addRelay();
        Athlete newAthlete = new AthleteBuilder("Gia","Tuong").setId(10000).setNationality("Vietnam").setLatestRecord(new LatestRecord(10,10 )).build();
        relays.addAthleteToRelay(newAthlete);
        relays.addAthleteToRelay(newAthlete);
        assertEquals(1,relays.relays.get(0).totalAthletes.size());
    }

    @Test
    void addAthleteToRelay_Fail (){
        for (int i = 0; i < 60; i++) {
            relays.addAthleteToRelay(new AthleteBuilder("Gia","Tuong").setId(10000 + i).setNationality("Vietnam").setLatestRecord(new LatestRecord(i,i)).build());
        }
        assertEquals(6, relays.relays.size());
        assertEquals(10, relays.relays.get(0).totalAthletes.size());
        assertEquals(10, relays.relays.get(1).totalAthletes.size());
        assertEquals(10, relays.relays.get(2).totalAthletes.size());
        assertEquals(10, relays.relays.get(3).totalAthletes.size());
        assertEquals(10, relays.relays.get(4).totalAthletes.size());
        assertEquals(4, relays.relays.get(5).totalAthletes.size());
    }

    @Test
    void addAthleteToRelayByRelayNumber(){
        relays.addRelay();
        Athlete newAthlete = new AthleteBuilder("Gia","Tuong").setId(10000).setNationality("Vietnam").setLatestRecord(new LatestRecord(10,10 )).build();
        relays.addAAthleteToRelay(newAthlete, 1);
        assertEquals(1,newAthlete.getIsInRelay());
        assertEquals(1, relays.relays.get(0).totalAthletes.size());
    }

    @Test
    void addAthleteToRelayByRelayNumber_Fail(){
        relays.addRelay();
        Athlete newAthlete = new AthleteBuilder("Gia","Tuong").setId(10000).setNationality("Vietnam").setLatestRecord(new LatestRecord(10,10 )).build();
        relays.addAAthleteToRelay(newAthlete, 2);
        assertEquals(-1,newAthlete.getIsInRelay());
        assertEquals(0, relays.relays.get(0).totalAthletes.size());
    }

    @Test
    void addAthleteToRelayByRelayNumber_NullAthlete(){
        relays.addRelay();
        relays.addAAthleteToRelay(null, 1);
        assertEquals(0, relays.relays.get(0).totalAthletes.size());
    }

    @Test
    void addAthleteToRelayByRelayNumber_AddExistingAthlete(){
        relays.addRelay();
        Athlete newAthlete = new AthleteBuilder("Gia","Tuong").setId(10000).setNationality("Vietnam").setLatestRecord(new LatestRecord(10,10 )).build();
        relays.addAAthleteToRelay(newAthlete,1);
        relays.addAAthleteToRelay(newAthlete,1);
        assertEquals(1,relays.relays.get(0).totalAthletes.size());
    }

    @Test
    void addAthleteToRelayByRelayNumber_FullInRelay(){
        relays.addRelay();
        for (int i = 0; i < 11; i++) {
            Athlete newAthlete = new AthleteBuilder("Gia","Tuong").setId(10000 + i).setNationality("Vietnam").setLatestRecord(new LatestRecord(i,i)).build();
            relays.addAAthleteToRelay(newAthlete,1);
        }
        assertEquals(10, relays.relays.get(0).totalAthletes.size());
    }
}