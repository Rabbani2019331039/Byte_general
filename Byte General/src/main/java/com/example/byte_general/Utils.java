package com.example.byte_general.cfapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utils {

    static JSONParser parse = new JSONParser();

    public static long getBeforeMilliSeconds(int day) {
        //Getting the current date
        Date date = new Date();
        //This method returns the time in millis
        long timeMilli = date.getTime();
        long val = Long.valueOf(day) * 24L * 60L * 60L * 1000L;
        long before = timeMilli - val;
        return before;
    }

    public static String[] getProblemTags(JSONObject problemJson) {

        try {
            JSONArray tagsArr = (JSONArray) parse.parse(String.valueOf(problemJson.get("tags")));
            String[] tags = new String[tagsArr.size()];
            for (int i = 0; i < tagsArr.size(); i++) {
                tags[i] = getString(tagsArr.get(i));
            }
            return tags;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String getString(Object obj) {
        return String.valueOf(obj);
    }

    public static JSONObject getJSONObjectFromString(String jsonString) {
        try {
            return (JSONObject) parse.parse(jsonString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static boolean matchTag(String[] tags, String[] problemTags) {
        for (String tag : tags) {
            for (String problemTag : problemTags) {
                if (tag.equalsIgnoreCase(problemTag)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean matchProblemTag(Problem problem, String[] tags) {
        return matchTag(tags, problem.getTags());
    }

    public static List<Submission> filterSubmissionsByTags(List<Submission> submissions, String[] tags) {
        List<Submission> filteredSubmissions = new ArrayList<>();
        for (Submission submission : submissions) {
            Problem problem = submission.getProblem();
            if (matchProblemTag(problem, tags)) {
                filteredSubmissions.add(submission);
            }
        }
        return filteredSubmissions;
    }

    public static List<Problem> filterProblemsByTags(List<Problem> problems, String[] tags) {
        List<Problem> filteredProblems = new ArrayList<>();
        for (Problem problem : problems) {
            if (matchProblemTag(problem, tags)) {
                filteredProblems.add(problem);
            }

        }
        return filteredProblems;
    }

    public static List<Submission> filterSubmissionsByVerdict(List<Submission> submissions, String verdict) {
        List<Submission> filteredSubmissions = new ArrayList<>();
        for (Submission submission : submissions) {
            if (submission.getVerdict().equalsIgnoreCase(verdict)) {
                filteredSubmissions.add(submission);
            }
        }
        return filteredSubmissions;
    }

    public static List<Submission> filterSubmissionsByTestset(List<Submission> submissions, String testset) {
        List<Submission> filteredSubmissions = new ArrayList<>();
        for (Submission submission : submissions) {
            if (submission.getTestset().equalsIgnoreCase(testset)) {
                filteredSubmissions.add(submission);
            }
        }
        return filteredSubmissions;
    }

    public static List<Submission> getUniqueSubmissions(List<Submission> submissions) {
        List<Submission> uniqueSubmissions = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (Submission submission : submissions) {
            Problem problem = submission.getProblem();
            String problemName = problem.getName();
            if (!map.containsKey(problemName)) {
                uniqueSubmissions.add(submission);
                map.put(problemName, 1);
            }

        }
        return uniqueSubmissions;
    }

    public static List<Submission> getUniqueAcceptedSubmissions(List<Submission> submissions) {
        List<Submission> filteredSubmissions = filterSubmissionsByVerdict(submissions, "OK");
        filteredSubmissions = filterSubmissionsByTestset(filteredSubmissions, "TESTS");
        filteredSubmissions = getUniqueSubmissions(filteredSubmissions);
        return filteredSubmissions;
    }

    public static List<Submission> getLatestSubmissions(List<Submission> submissions, int day) {
        List<Submission> uniqueAcceptedSubmissions = getUniqueAcceptedSubmissions(submissions);
        List<Submission> latestSubmissions = new ArrayList<>();
        long beforeMillis = getBeforeMilliSeconds(day);
        for (Submission submission : uniqueAcceptedSubmissions) {
            String timeSecondStr = submission.getCreationTimeSeconds();
            long timeInSecond = Long.parseLong(timeSecondStr);
            long timeInMillis = timeInSecond * 1000L;
            if (timeInMillis >= beforeMillis) {
                latestSubmissions.add(submission);
            }
        }
        return latestSubmissions;
    }

    public static List<Contest> getLatestContests(List<Contest> contests, int day) {
        List<Contest> latestContests = new ArrayList<>();
        long beforeMillis = getBeforeMilliSeconds(day);
        for (Contest contest : contests) {
            String timeSecondStr = contest.getRatingUpdateTimeSeconds();
            long timeInSecond = Long.parseLong(timeSecondStr);
            long timeInMillis = timeInSecond * 1000L;
            if (timeInMillis >= beforeMillis) {
                latestContests.add(contest);
            }
        }
        return latestContests;
    }

}
