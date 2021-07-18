package com.diogo.kick;

public class ItemsTeamsDetailsResults {

    private String id;
    private String imageUrl1;
    private String imageUrl2;
    private String name1;
    private String name2;
    private String round;
    private String score;
    private String status;
    private String date;
    private String win;

    public ItemsTeamsDetailsResults(String id, String imageUrl1, String imageUrl2, String name1, String name2, String round, String score, String status, String date, String win){
        this.id = id;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.name1 = name1;
        this.name2 = name2;
        this.round = round;
        this.score = score;
        this.status = status;
        this.date = date;
        this.win = win;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getRound() {
        return round;
    }

    public String getScore() {
        return score;
    }

    public String getStatus() {
        return status;
    }

    public String getWin() {
        return win;
    }

    public String getDate() {
        String d = date.split("T")[0];
        String dt = d.split("-")[2] + "." + d.split("-")[1];
        return dt;
    }
}
