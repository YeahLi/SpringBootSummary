package com.henry.springboot.ioc;

public class Account {
    private Integer id;
    private String name;
    private Float money;

    public Account(Integer id, String name, Float money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public String getName() {
        return name;
    }

    public Float getMoney() {
        return money;
    }
}
