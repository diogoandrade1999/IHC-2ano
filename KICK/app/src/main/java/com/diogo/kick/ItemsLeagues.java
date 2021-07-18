package com.diogo.kick;

public class ItemsLeagues implements Comparable<ItemsLeagues> {

    private String id;
    private String imageUrl;
    private String name;
    private String country;
    private String flag;

    public ItemsLeagues(String id, String imageUrl, String name, String country, String flag){
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.country = country;
        this.flag = flag;
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

    public String getCountry() {
        return country;
    }

    public String getFlag() {
        return flag;
    }

    @Override
    public int compareTo(ItemsLeagues o) {
        return getCountry().compareTo(getCountry());
    }
}
