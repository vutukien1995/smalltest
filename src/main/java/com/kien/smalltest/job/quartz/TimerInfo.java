package com.kien.smalltest.job.quartz;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kienvt
 */
@Getter
@Setter
public class TimerInfo {

    private int totalFireCount;
    private int remainingFireCount;
    private boolean runForever;
    private long repeatIntervalMs;
    private long initialOffsetMs;
    private String callbackData;

}
