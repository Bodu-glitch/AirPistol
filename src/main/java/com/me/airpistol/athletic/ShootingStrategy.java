package com.me.airpistol.athletic;

public interface ShootingStrategy extends Cloneable {
    public float checkShootingScore(float distanceFromTarget) throws Exception;

    public void shoot(float distanceFromTarget);

    public boolean checkCurrentShots();

    public float getShootingScore();

    public void setCurrentShots(int currentShots);

    ShootingStrategy clone();
}