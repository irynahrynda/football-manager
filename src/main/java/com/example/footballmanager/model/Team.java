package com.example.footballmanager.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
    @Column(name = "commission_transfer_percent")
    private Double commissionTransferPercent;
    private BigDecimal balance;

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Double getCommissionTransferPercent() {
        return commissionTransferPercent;
    }

    public void setCommissionTransferPercent(Double commissionTransferPercent) {
        this.commissionTransferPercent = commissionTransferPercent;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Team{"
                + "id=" + id
                + ", teamName='" + teamName + '\''
                + ", commissionTransferPercent=" + commissionTransferPercent
                + ", balance=" + balance
                + '}';
    }
}
