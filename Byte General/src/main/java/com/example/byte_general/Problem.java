package com.example.byte_general.cfapi;


import org.json.simple.JSONObject;

public class Problem {
       
    private String contestId;
    private String index;
    private String name;
    private String type;
    private String points;
    private String rating;
    private String[] tags;

    public Problem(JSONObject problemJson) {
        contestId = Utils.getString(problemJson.get("contestId"));
        index = Utils.getString(problemJson.get("index"));
        name = Utils.getString(problemJson.get("name"));
        type = Utils.getString(problemJson.get("type"));
        points = Utils.getString(problemJson.get("points"));
        rating = Utils.getString(problemJson.get("rating"));
        tags = Utils.getProblemTags(problemJson);
    }
    
    public Problem(String problemString) {
       this(Utils.getJSONObjectFromString(problemString));
    }

    @Override
    public String toString() {
        return "Problem{" + "contestId=" + contestId + ", index=" + index + ", name=" + name + ", type=" + type + ", points=" + points + ", rating=" + rating + '}';
    }

    
    
    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
    
    
}
