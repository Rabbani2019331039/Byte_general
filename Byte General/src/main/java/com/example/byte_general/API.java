
package com.example.byte_general.cfapi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class API {

    
    public static List<Submission> getSubmissions(String handleName) {
        
        String submissionUrl = "https://codeforces.com/api/user.status?handle="+handleName;
        List<Submission> submissions = new ArrayList<>();
        try {
            URL url = new URL(submissionUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                //String sampleJsonString = "{name:'Shimla', reg:'2019331106}";
                //int a = 5;
                //String str = String.valueOf(a);

                JSONParser parser = new JSONParser();
                JSONObject jSONObject = (JSONObject) parser.parse(String.valueOf(informationString));
                String status = (String) jSONObject.get("status");
                if (status.equals("OK")) {
                    Object result = jSONObject.get("result");
                    JSONArray submissionArr = (JSONArray) parser.parse(String.valueOf(result));

                    for (int i = 0; i < submissionArr.size(); i++) {
                        JSONObject submissionObj = (JSONObject) parser.parse(String.valueOf(submissionArr.get(i)));
                        Submission submission = new Submission(submissionObj);
                        submissions.add(submission);
                    }
                    return submissions;

                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return submissions;
    }
    
     public static List<Contest> getContests(String handleName) {
        
        String contestUrl = "https://codeforces.com/api/user.rating?handle="+handleName;
         List<Contest> contests = new ArrayList<>();
        try {
            URL url = new URL(contestUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                // jsonString -> jsonObject
                JSONObject jSONObject = (JSONObject) parse.parse(String.valueOf(informationString));
                // accessing status property of jsonObject
                String status = (String) jSONObject.get("status");
                if (status.equals("OK")) {
                    Object result = jSONObject.get("result");
                    JSONArray contestArr = (JSONArray) parse.parse(String.valueOf(result));

                    for (int i = 0; i < contestArr.size(); i++) {
                        Object obj = contestArr.get(i);
                        JSONObject contestObj = (JSONObject) parse.parse(String.valueOf(obj));
                        Contest contest = new Contest(contestObj);
                        contests.add(contest);
                    }
                    return contests;

                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return contests;
    }
    
    
    
    public static void main(String[] args) {
        // codeforces handle name

        Scanner scanner=new Scanner(System.in);


        String handleName = "tourist";
        
        // get all the submissions
        List<Submission> submissions = getSubmissions(handleName);
        
        // filter submissions, only accepted, one for each problem
        List<Submission> acceptedSubmissions = Utils.getUniqueAcceptedSubmissions(submissions);
        
        // tags to filter
        String[] tags = new String[]{"math"};
        
        // filtered submissions
        List<Submission> filteredSubmissions = Utils.filterSubmissionsByTags(acceptedSubmissions, tags);
       
        System.out.println("------------------------------------");
        System.out.println("All the accepted submission for tag = " + Arrays.toString(tags));
        for(Submission submission : filteredSubmissions){
            System.out.println(submission.getVerdict() + " " + submission.getProblem().getName() + " " + Arrays.toString(submission.getProblem().getTags()));   
        }
        
         System.out.println("------------------------------------");
         
         int day = 60;
        // get latest submission, in day, 60 means 60 days(2 month).
        List<Submission> latestSubmissions = Utils.getLatestSubmissions(submissions, day);
        System.out.println("Latest submission from day = " + day);
        for(Submission submission : latestSubmissions){
            System.out.println(submission.getVerdict() + " " + submission.getProblem().getName());   
        }
        
        System.out.println("------------------------------------");
        
        // get all the contest for a handle
        List<Contest> contests = getContests(handleName);
        
        // get latest contest, in day, 60 means 60 days(2 month).
        List<Contest> latestContests = Utils.getLatestContests(contests, day);
        System.out.println("Latest contest from day = " + day);
        for(Contest contest: latestContests){
            System.out.println(contest);
        }


        int latestSubmissionCount= latestSubmissions.size();
        int totalSubmissionCount=  submissions.size();
        int totalAcceptedSubmissionCount= acceptedSubmissions.size();


        double ratioOfAcceptedAndTotalSubmission= ((totalAcceptedSubmissionCount*1.0)/totalSubmissionCount)*100;
        double ratioOfLatestAndTotalSubmission= ((latestSubmissionCount*1.0)/totalSubmissionCount)*100;

        int lastContestNo=latestContests.size()-1;
        String currentRatingString= contests.get(lastContestNo).getNewRating();
        double currentRating=Double.parseDouble(currentRatingString);
        double currentRatingPercent= ((currentRating*1.0)/3000)*100;

        System.out.println("------------------------------------");

        System.out.println("Ratio of accepted and total submission "+ratioOfAcceptedAndTotalSubmission);
        System.out.println("Ratio of latest and total submission "+ratioOfLatestAndTotalSubmission);
        System.out.println("Current rating percent "+currentRatingPercent);

        double actualPerformanceRating= ((ratioOfAcceptedAndTotalSubmission*20)/100)+((ratioOfLatestAndTotalSubmission*20)/100)+
                ((currentRatingPercent*60)/100);


        System.out.println("Calculated Performance Rating--->"+actualPerformanceRating);

        System.out.println("------------------------------------");
    }
}
