package com.diogo.kick;

public class ItemsLeaguesDetails {

    private String id;
    private String imageUrl;
    private String name;
    private String rank;
    private String goalsFor;
    private String goalsAgainst;
    private String points;
    private String matchsPlayed;

    public ItemsLeaguesDetails(String id, String imageUrl, String name, String rank, String goalsFor, String goalsAgainst, String points, String matchsPlayed){
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.rank = rank;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.points = points;
        this.matchsPlayed = matchsPlayed;
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

    public String getRank() {
        return rank;
    }

    public String getGoalsFor() {
        return goalsFor;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    public String getPoints() {
        return points;
    }

    public String getMatchsPlayed() {
        return matchsPlayed;
    }
}
