package com.example.byte_general.cfapi;

import org.json.simple.JSONObject;

public class Submission {

    private String id;
    private String contestId;
    private String creationTimeSeconds;
    private String programmingLanguage;
    private String verdict;
    private String testset;
    private String timeConsumedMillis;
    private String memoryConsumedBytes;
    private Problem problem;

    public Submission(JSONObject submissionJson) {
        id = Utils.getString(submissionJson.get("id"));
        contestId = Utils.getString(submissionJson.get("contestId"));
        creationTimeSeconds = Utils.getString(submissionJson.get("creationTimeSeconds"));
        programmingLanguage = Utils.getString(submissionJson.get("programmingLanguage"));
        verdict = Utils.getString(submissionJson.get("verdict"));
        testset = Utils.getString(submissionJson.get("testset"));
        timeConsumedMillis = Utils.getString(submissionJson.get("timeConsumedMillis"));
        memoryConsumedBytes = Utils.getString(submissionJson.get("memoryConsumedBytes"));
        problem = new Problem(Utils.getString(submissionJson.get("problem")));
    }

    public String getTimeConsumedMillis() {
        return timeConsumedMillis;
    }

    public void setTimeConsumedMillis(String timeConsumedMillis) {
        this.timeConsumedMillis = timeConsumedMillis;
    }

    public String getMemoryConsumedBytes() {
        return memoryConsumedBytes;
    }

    public void setMemoryConsumedBytes(String memoryConsumedBytes) {
        this.memoryConsumedBytes = memoryConsumedBytes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public String getCreationTimeSeconds() {
        return creationTimeSeconds;
    }

    public void setCreationTimeSeconds(String creationTimeSeconds) {
        this.creationTimeSeconds = creationTimeSeconds;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getTestset() {
        return testset;
    }

    public void setTestset(String testset) {
        this.testset = testset;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
