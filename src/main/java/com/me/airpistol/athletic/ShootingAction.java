package com.me.airpistol.athletic;

public class ShootingAction implements ShootingStrategy, Cloneable {

    float shootingScore;
    int currentShots;
    int time = 0; // in seconds

    public ShootingAction() {

    }

    @Override
    public float getShootingScore() {
        return this.shootingScore;
    }

    public float calculateScore(float distanceFromTarget, // khoảng cách từ tâm đến biên
                                float radius, // bán kính
                                int circle, // vòng trong vòng ngoài
                                float border) throws Exception {
        if (distanceFromTarget > radius || distanceFromTarget < border && distanceFromTarget != 0) {
            throw new Exception("Error: It is out of range");
        } else {
            if (distanceFromTarget == 0) {
                return 0;
            }
            //khoảng cách của các vòng trong
            float smallerRadius = (float) Math.round(((radius - border) / 10f) * 100) / 100;
//            System.out.println("smallerRadius: " + smallerRadius);
            int j = 0;
            // Sẽ không thể chạm vào 13.75
            for (float i = border; i <= radius; i = (float) Math.round((i + smallerRadius) * 1000) / 1000) {
                j++;
//                System.out.println("i: " + i);
                //nên chỗ này cộng lên khoảng cách của các vòng trong
                if (distanceFromTarget <= i + smallerRadius) {
                    return (float) Math.round((circle + (float) (10 - j) / 10) * 10) / 10;
                }
            }
            return 0;
        }
    }

    @Override
    public void setCurrentShots(int currentShots) {
        this.currentShots = currentShots;
    }

    @Override
    public float checkShootingScore(float distanceFromTarget) throws Exception {
        if (distanceFromTarget < 0) {
            throw new Exception("Distance can not be < 0");
        }
        // vòng 10: khoảng cách từ tâm đến biên là 5.75mm
        if (distanceFromTarget <= 5.75f) {
            return calculateScore(distanceFromTarget, 5.75f, 10, 0);
        } else if (distanceFromTarget <= 13.75) {
            return calculateScore(distanceFromTarget, 13.75f, 9, 5.75f);
        } else if (distanceFromTarget <= 21.75) {
            return calculateScore(distanceFromTarget, 21.75f, 8, 13.75f);
        } else if (distanceFromTarget <= 29.75) {
            return calculateScore(distanceFromTarget, 29.75f, 7, 21.75f);
        } else if (distanceFromTarget <= 37.75) {
            return calculateScore(distanceFromTarget, 37.75f, 6, 29.75f);
        } else if (distanceFromTarget <= 45.75) {
            return calculateScore(distanceFromTarget, 45.75f, 5, 37.75f);
        } else if (distanceFromTarget <= 53.75) {
            return calculateScore(distanceFromTarget, 53.75f, 4, 45.75f);
        } else if (distanceFromTarget <= 61.75) {
            return calculateScore(distanceFromTarget, 61.75f, 3, 53.75f);
        } else if (distanceFromTarget <= 69.75) {
            return calculateScore(distanceFromTarget, 69.75f, 2, 61.75f);
        } else if (distanceFromTarget <= 77.75) {
            return calculateScore(distanceFromTarget, 77.75f, 1, 69.75f);
        }
        return 0;
    }

    @Override
    public void shoot(float distanceFromTarget) {
        try {
            if (this.checkCurrentShots()) {
                distanceFromTarget = (float) Math.round(distanceFromTarget * 1000) / 1000;
                this.shootingScore = Math.round(shootingScore * 100000) / 100000.0f;
                float addScore = Math.round(checkShootingScore(distanceFromTarget) * 100000) / 100000.0f;
//                System.out.println("Shooting score: " + (float) Math.round(checkShootingScore(distanceFromTarget) * 10) / 10f );
                this.shootingScore = (float) (Math.round((shootingScore + addScore) * 10)) / 10;
//                System.out.println("Total shooting score: " + this.shootingScore);
                this.currentShots++;
            } else {
                System.err.println("Shoots is full");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean checkCurrentShots() {
        return this.currentShots < 60;
    }

    @Override
    public ShootingAction clone() {
        try {
            ShootingAction clone = (ShootingAction) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
