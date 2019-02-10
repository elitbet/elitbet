package org.elitbet.statistic.model;

public class Request {

    private EventType eventType;
    private Statistic statistic;

    public Request() {
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Request{");
        sb.append("eventType=").append(eventType);
        sb.append(", statistic=").append(statistic);
        sb.append('}');
        return sb.toString();
    }
}
