package com.me.airpistol.eleminationRound;

import com.me.airpistol.athletic.Athlete;

import java.util.ArrayList;
import java.util.List;

public abstract class RelayAbstract implements RelayInterface, OperationInterface {
    protected int relayNumber;
    List<Athlete> totalAthletes = new ArrayList<>();
    RelayAbstract relayRecord;

    @Override
    public abstract void addAthlete(Athlete newAthlete);

    @Override
    public abstract boolean checkIsFull();

    @Override
    public abstract void startRelay();

    @Override
    public abstract void endRelay();

}
