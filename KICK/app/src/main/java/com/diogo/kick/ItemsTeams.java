package com.diogo.kick;

public class ItemsTeams {

    private String id;
    private String imageUrl;
    private String name;

    public ItemsTeams(String id, String imageUrl, String name){
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getimageUrl() {
        return imageUrl;
    }

    public String getname() {
        return name;
    }

}