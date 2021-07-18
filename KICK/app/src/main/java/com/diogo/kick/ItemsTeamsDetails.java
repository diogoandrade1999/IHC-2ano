package com.diogo.kick;

public class ItemsTeamsDetails {

    private String id;
    private String name;
    private String position;
    private String number;

    public ItemsTeamsDetails(String id, String name, String position, String number){
        this.id = id;
        this.name = name;
        this.position = position;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public String getname() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getNumber() {
        return number;
    }
}
