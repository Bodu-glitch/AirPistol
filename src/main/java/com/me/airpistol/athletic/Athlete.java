package com.me.airpistol.athletic;

public class Athlete {
    int id;
    String firstName;
    String lastName;
    ShootingStrategy shootingAction;
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

    public Athlete clone() {
        try {
            return (Athlete) super.clone(); // Sử dụng clone từ Object
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }

//    @Override
//    public String toString() {
//        return "Athlete{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", shooting Score=" + shootingAction.getShootingScore() +
//                ", nationality='" + nationality + '\'' +
//                ", isInRelay=" + isInRelay +
//                ", latestRecord=" + latestRecord +
//                '}';
//    }
}

