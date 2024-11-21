package com.me.airpistol.athletic;

public interface ShootingStrategy {
    public float checkShootingScore(float distanceFromTarget) throws Exception;
    public void shoot(float distanceFromTarget);
    public boolean checkCurrentShots ();
    public float getShootingScore();
}