package com.me.airpistol.athletic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShootingActionTest {

    ShootingAction shootingAction;

    @BeforeEach
    void setUp() {
        shootingAction = new ShootingAction();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void calculateScore() throws Exception {
        float expected = 8.4f;
        float actual = shootingAction.calculateScore(18.11f, 21.75f, 8, 13.75f);
        assertEquals(expected, actual);
    }

    @Test
    void calculateScore_distanceSmallerThanSmallerCircle() throws Exception {
        assertThrows(Exception.class, () -> {
            shootingAction.calculateScore(12f, 21.75f, 8, 13.75f);
        });
    }

    @Test
    void calculateScore_distanceLargerThanLargerCircle() throws Exception {
        assertThrows(Exception.class, () -> {
            shootingAction.calculateScore(22f, 21.75f, 8, 13.75f);
        });
    }

    @Test
    void calculateScore_distanceEqualToZero() throws Exception {
        float expected = 11;
        float actual = shootingAction.calculateScore(0, 0.575f, 1, 5.75f);
        assertEquals(expected, actual);
    }

    @Test
    void calculateScore_DistanceOutOfRange() {
        assertThrows(Exception.class, () -> {
            shootingAction.calculateScore(22f, 21.75f, 8, 13.75f);
        });
    }

    @Test
    void checkShootingScore_Case9() throws Exception {
        float expected = 9.8f;
        float actual = shootingAction.checkShootingScore(6.56f);
        assertEquals(expected, actual);
    }

    @Test
    void checkShootingScore_Case8() throws Exception {
        float expected = 8.4f;
        float actual = shootingAction.checkShootingScore(18.2f);
        assertEquals(expected, actual);
    }

    @Test
    void checkShootingScore_Case7() throws Exception {
        float expected = 7.1f;
        float actual = shootingAction.checkShootingScore(28.3f);
        assertEquals(expected, actual);
    }

    @Test
    void checkShootingScore_Case6() throws Exception {
        float expected = 6.0f;
        float actual = shootingAction.checkShootingScore(37.75f);
        assertEquals(expected, actual);
    }

    @Test
    void checkShootingScore_Case5() throws Exception {
        float expected = 5.1f;
        float actual = shootingAction.checkShootingScore(44.95f);
        assertEquals(expected, actual);
    }

    @Test
    void checkShootingScore_Case4() throws Exception {
        float expected = 4.3f;
        float actual = shootingAction.checkShootingScore(51.35f);
        assertEquals(expected, actual);
    }

    @Test
    void checkShootingScore_Case3() throws Exception {
        float expected = 3.4f;
        float actual = shootingAction.checkShootingScore(58.45f);
        assertEquals(expected, actual);
    }

    @Test
    void checkShootingScore_Case2() throws Exception {
        float expected = 2.5f;
        float actual = shootingAction.checkShootingScore(65.75f);
        assertEquals(expected, actual);
    }

    @Test
    void checkShootingScore_Case1() throws Exception {
        float expected = 1.6f;
        float actual = shootingAction.checkShootingScore(72.75f);
        assertEquals(expected, actual);
    }

    @Test
    void checkShootingScore_OutOfRange() {
        assertThrows(Exception.class, () -> {
            shootingAction.checkShootingScore(-1);
        });
    }

    @Test
    void checkShootingScore_Case0() throws Exception {
        float expected = 0;
        float actual = shootingAction.checkShootingScore(80f);
        assertEquals(expected, actual);
    }

    @Test
    void shoot() {
        shootingAction.shoot(5.75f);
        assertEquals(1, shootingAction.currentShots);
        assertEquals(10.0f, shootingAction.shootingScore);
    }

    @Test
    void shoot_InvalidDistance() {
        shootingAction.shoot(-1);
        assertEquals(0, shootingAction.currentShots);
        assertEquals(0, shootingAction.shootingScore);

        shootingAction.shoot(5.75f);
        assertEquals(1, shootingAction.currentShots);
        assertEquals(10.0f, shootingAction.shootingScore);
    }

    @Test
    void shoot_Full() {
        for (int i = 0; i < 62; i++) {
            shootingAction.shoot(5.75f);
        }

        shootingAction.shoot(5.75f);
        assertEquals(60, shootingAction.currentShots);
        assertEquals(600.0f, shootingAction.shootingScore);
    }

    @Test
    void checkCurrentShots() {
        assertTrue(shootingAction.checkCurrentShots());
    }

    @Test
    void checkCurrentShots_Full() {
        for (int i = 0; i < 61; i++) {
            shootingAction.shoot(5.75f);
        }

        assertFalse(shootingAction.checkCurrentShots());
    }

    @Test
    void setCurrentShots() {
        shootingAction.setCurrentShots(10);
        assertEquals(10, shootingAction.currentShots);
    }

    @Test
    void deepClone() {
        ShootingAction clone = shootingAction.clone();
        shootingAction.shoot(5.75f);
        assertNotEquals(shootingAction.currentShots, clone.currentShots);
        assertNotEquals(shootingAction.shootingScore, clone.shootingScore);
    }
}