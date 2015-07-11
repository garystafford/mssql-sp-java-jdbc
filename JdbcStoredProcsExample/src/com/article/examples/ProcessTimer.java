package com.article.examples;

/**
 * Timer to measure duration of database calls
 *
 * @author Gary A. Stafford
 */
public class ProcessTimer {

    private long startTime;
    private long endTime;

    /**
     * @return duration in milliseconds
     */
    public long getDuration() {
        return Math.abs(startTime - endTime) / 1000000;
    }

    /**
     * @param startTime the startTime to set in nanoseconds
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * @param endTime the endTime to set in nanoseconds
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
