package ru.kazemirov.sellerservice.domain;

import javax.persistence.*;

@Entity
public class Seller {
    @Id
    private Integer id;

    private String name;
    private String inn;
    private String address;

    public Seller() {
    }

    public Seller(Integer id, String name, String inn, String address) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
