package com.elitbet.model;

import com.elitbet.model.odds.Odd;

import java.util.ArrayList;
import java.util.List;

public class OddsContainer {

    private String id;
    private List<Odd> odds = new ArrayList<>();

    public OddsContainer() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Odd> getOdds() {
        return odds;
    }

    public void setOdds(List<Odd> odds) {
        this.odds = odds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OddsContainer{");
        sb.append("id='").append(id).append('\'');
        sb.append(", odds=").append(odds);
        sb.append('}');
        return sb.toString();
    }
}