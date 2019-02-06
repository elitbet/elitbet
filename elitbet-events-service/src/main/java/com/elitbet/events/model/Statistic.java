package com.elitbet.events.model;

import javax.persistence.*;
import java.util.Map;

@Entity(name="STATISTIC")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "STATISTIC")
public abstract class Statistic {

    @Id
    @Column(name="STATISTIC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statisticId;

    @OneToOne(mappedBy = "statistic")
    private Event event;

    public Statistic() {
    }

    public Long getStatisticId() {
        return statisticId;
    }

    public void setStatisticId(Long statisticId) {
        this.statisticId = statisticId;
    }

    public abstract String names();

    public abstract Map<String,String> getDataMap();
}