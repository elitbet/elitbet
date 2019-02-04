package com.elitbet.model;

public class EventOrder {

    private String eventId;
    private Priority priority;
    private boolean load_1x2_FullTime;
    private boolean load_1x2_FirstHalf;
    private boolean load_1x2_SecondHalf;
    private boolean load_OverUnder_FullTime;
    private boolean load_OverUnder_FirstHalf;
    private boolean load_OverUnder_SecondHalf;

    public EventOrder() {
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isLoad_1x2_FullTime() {
        return load_1x2_FullTime;
    }

    public void setLoad_1x2_FullTime(boolean load_1x2_FullTime) {
        this.load_1x2_FullTime = load_1x2_FullTime;
    }

    public boolean isLoad_1x2_FirstHalf() {
        return load_1x2_FirstHalf;
    }

    public void setLoad_1x2_FirstHalf(boolean load_1x2_FirstHalf) {
        this.load_1x2_FirstHalf = load_1x2_FirstHalf;
    }

    public boolean isLoad_1x2_SecondHalf() {
        return load_1x2_SecondHalf;
    }

    public void setLoad_1x2_SecondHalf(boolean load_1x2_SecondHalf) {
        this.load_1x2_SecondHalf = load_1x2_SecondHalf;
    }

    public boolean isLoad_OverUnder_FullTime() {
        return load_OverUnder_FullTime;
    }

    public void setLoad_OverUnder_FullTime(boolean load_OverUnder_FullTime) {
        this.load_OverUnder_FullTime = load_OverUnder_FullTime;
    }

    public boolean isLoad_OverUnder_FirstHalf() {
        return load_OverUnder_FirstHalf;
    }

    public void setLoad_OverUnder_FirstHalf(boolean load_OverUnder_FirstHalf) {
        this.load_OverUnder_FirstHalf = load_OverUnder_FirstHalf;
    }

    public boolean isLoad_OverUnder_SecondHalf() {
        return load_OverUnder_SecondHalf;
    }

    public void setLoad_OverUnder_SecondHalf(boolean load_OverUnder_SecondHalf) {
        this.load_OverUnder_SecondHalf = load_OverUnder_SecondHalf;
    }


}
