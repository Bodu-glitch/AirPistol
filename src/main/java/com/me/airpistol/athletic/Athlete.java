package com.me.airpistol.athletic;

public class Athlete implements Cloneable {
    int id;
    String firstName;
    String lastName;
    public ShootingStrategy shootingAction;
    String nationality;
    int isInRelay;
    LatestRecord latestRecord;

    public Athlete(AthleteBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.shootingAction = builder.shootingAction;
        this.nationality = builder.nationality;
        this.latestRecord = builder.latestRecord;
        this.isInRelay = builder.isInRelay;
    }

    public int getId() {
        return this.id;
    }

    public int getIsInRelay() {
        return this.isInRelay;
    }

    public float getScore() {
        return this.shootingAction.getShootingScore();
    }

    public void setInRelay(int isInRelay) {
        this.isInRelay = isInRelay;
    }

    public LatestRecord getLatestRecord() {
        return this.latestRecord;
    }

    public void shoot(float distance) {
        this.shootingAction.shoot(distance);
    }

    public void setDefaultShootingAction() {
        this.shootingAction = new ShootingAction();
    }

    @Override
    public Athlete clone() {
        try {
            Athlete clone = (Athlete) super.clone();
            clone.shootingAction = this.shootingAction.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }


    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", shooting Score=" + shootingAction.getShootingScore() +
                ", nationality='" + nationality + '\'' +
                ", isInRelay=" + isInRelay +
                ", latestRankRecord=" + latestRecord.rankRecord +
                '}';
    }
}

