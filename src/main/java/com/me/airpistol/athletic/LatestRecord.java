package com.me.airpistol.athletic;

import java.util.ArrayList;
import java.util.List;

public class LatestRecord {
    float scoreRecord;
    int rankRecord;
    public static List<LatestRecord> existingRecords = new ArrayList<LatestRecord>();

    public LatestRecord(float scoreRecord, int rankRecord) {
        this.scoreRecord = scoreRecord;
        this.rankRecord = rankRecord;
    }

    public int getRankRecord() {
        return rankRecord;
    }
}
