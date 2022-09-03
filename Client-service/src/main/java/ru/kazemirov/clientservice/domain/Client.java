package ru.kazemirov.clientservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    private Integer id;
    private String name;
    private String surname;
    private String city;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Client() {
    }

    public Client(Integer id, String name, String surname, String city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
    }
}
