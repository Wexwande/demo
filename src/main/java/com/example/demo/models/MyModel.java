package com.example.demo.models;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "requests")
public class MyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "recent_date")
    private LocalDate recentDate;

    @Column(name = "status_readiness")
    private Boolean statusReadiness;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "customer_information")
    private String customerInformation;

    public MyModel(String name, LocalDate startDate, LocalDate recentDate, Boolean statusReadiness, String responsible, String customerInformation) {
        this.name = name;
        this.startDate = startDate;
        this.recentDate = recentDate;
        this.statusReadiness = statusReadiness;
        this.responsible = responsible;
        this.customerInformation = customerInformation;
    }

    public MyModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getRecentDate() {
        return recentDate;
    }

    public void setRecentDate(LocalDate recentDate) {
        this.recentDate = recentDate;
    }

    public Boolean getStatusReadiness() {
        return statusReadiness;
    }

    public void setStatusReadiness(Boolean statusReadiness) {
        this.statusReadiness = statusReadiness;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getCustomerInformation() {
        return customerInformation;
    }

    public void setCustomerInformation(String customerInformation) {
        this.customerInformation = customerInformation;
    }
}
