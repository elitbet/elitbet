package com.elitbet.live.scores.parser.model;

import javax.persistence.*;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "load_1x2_full_time")
    private boolean load_1x2_FullTime;

    @Column(name = "load_1x2_first_half")
    private boolean load_1x2_FirstHalf;

    @Column(name = "load_1x2_second_half")
    private boolean load_1x2_SecondHalf;

    @Column(name = "load_over_under_full_time")
    private boolean load_OverUnder_FullTime;

    @Column(name = "load_over_under_first_half")
    private boolean load_OverUnder_FirstHalf;

    @Column(name = "load_over_under_second_half")
    private boolean load_OverUnder_SecondHalf;

    public Request() {
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
