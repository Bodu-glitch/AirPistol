package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;

public interface RelayInterface {
    public void addAthlete(Athlete newAthlete);
    public boolean checkIsFull();
    public void startRelay();
    public void endRelay();

}
