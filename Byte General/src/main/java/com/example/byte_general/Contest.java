package com.example.byte_general.cfapi;


import org.json.simple.JSONObject;

public class Contest {
    
    private String contestId;
    private String contestName;
    private String handle;
    private String rank;
    private String ratingUpdateTimeSeconds;
    private String oldRating;
    private String newRating;


    public Contest(JSONObject contestJson) {
        contestId = Utils.getString(contestJson.get("contestId"));
        contestName = Utils.getString(contestJson.get("contestName"));
        handle = Utils.getString(contestJson.get("handle"));
        rank = Utils.getString(contestJson.get("rank"));
        ratingUpdateTimeSeconds = Utils.getString(contestJson.get("ratingUpdateTimeSeconds"));
        oldRating = Utils.getString(contestJson.get("oldRating"));
        newRating = Utils.getString(contestJson.get("newRating"));
    }



    @Override
    public String toString() {
        return "Contest{" +
                ", contestName='" + contestName + '\'' +
                ", rank='" + rank + '\'' +
                ", oldRating='" + oldRating + '\'' +
                ", newRating='" + newRating + '\'' +
                '}';
    }

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRatingUpdateTimeSeconds() {
        return ratingUpdateTimeSeconds;
    }

    public void setRatingUpdateTimeSeconds(String ratingUpdateTimeSeconds) {
        this.ratingUpdateTimeSeconds = ratingUpdateTimeSeconds;
    }

    public String getOldRating() {
        return oldRating;
    }

    public void setOldRating(String oldRating) {
        this.oldRating = oldRating;
    }

    public String getNewRating() {
        return newRating;
    }

    public void setNewRating(String newRating) {
        this.newRating = newRating;
    }
}
