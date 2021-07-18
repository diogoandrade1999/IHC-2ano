package com.diogo.kick;

public class ItemsLivescores {

    private String idf;
    private String id1;
    private String id2;
    private String imageUrl1;
    private String imageUrl2;
    private String name1;
    private String name2;
    private String round;
    private String scoreHome;
    private String scoreAway;
    private String status;
    private String date;

    public ItemsLivescores(String idf,String id1, String id2,String imageUrl1, String imageUrl2, String name1, String name2, String round, String scoreHome, String scoreAway, String status, String date){
        this.idf = idf;
        this.id1 = id1;
        this.id2 = id2;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.name1 = name1;
        this.name2 = name2;
        this.round = round;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
        this.status = status;
        this.date = date;
    }

    public String getIdf() {
        return idf;
    }

    public String getId1() {
        return id1;
    }

    public String getId2() {
        return id2;
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

    public String getScoreHome() {
        return scoreHome;
    }

    public String getScoreAway() {
        return scoreAway;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        String d = date.split("T")[0];
        String dt = d.split("-")[2] + "." + d.split("-")[1];
        return dt;
    }
}
