package com.elitbet.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="CLIENT")
public class Client {

    @Id
    @Column(name = "CLIENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;

    @Column(name="NAME")
    private String name;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PASSWORD")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Wager> wagerList = new ArrayList<>();

    @Column(name = "BANK_VALUE")
    private double bankValue;

    public Client() {
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Wager> getWagerList() {
        return wagerList;
    }

    public void setWagerList(List<Wager> wagerList) {
        this.wagerList = wagerList;
    }

    public double getBankValue() {
        return bankValue;
    }

    public void setBankValue(double bankValue) {
        this.bankValue = bankValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientId, client.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("clientId=").append(clientId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", bankValue=").append(bankValue);
        sb.append('}');
        return sb.toString();
    }


}