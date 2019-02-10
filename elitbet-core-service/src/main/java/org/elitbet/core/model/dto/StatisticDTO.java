package org.elitbet.core.model.dto;

import java.util.Objects;

public class StatisticDTO {

    private Integer eventID;
    private Object statistic;

    public StatisticDTO() {
    }

    public StatisticDTO(EventDTO eventDTO){
        this.eventID = eventDTO.getEventID();
        this.statistic = eventDTO.getStatistic();
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Object getStatistic() {
        return statistic;
    }

    public void setStatistic(Object statistic) {
        this.statistic = statistic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticDTO that = (StatisticDTO) o;
        return Objects.equals(eventID, that.eventID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventID);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StatisticDTO{");
        sb.append("eventID=").append(eventID);
        sb.append(", statistic=").append(statistic);
        sb.append('}');
        return sb.toString();
    }
}
