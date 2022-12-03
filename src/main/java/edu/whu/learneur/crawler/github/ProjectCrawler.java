package edu.whu.learneur.crawler.github;

import edu.whu.learneur.crawler.entity.Project;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProjectCrawler {
    static String url="https://api.github.com/search/repositories?q=";

    public static List<Project> getProjects(String url) throws  Exception {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("GET");

        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            System.out.println("GET request not worked");
            return null;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String responseStr = response.toString();

        return parse(responseStr);

    }
    private static List<Project> runScrawler(List<String> knowledges) throws Exception {
        List<Project> res = new ArrayList<>();
        for(String knowledge : knowledges) {
            String fullUrl = url + knowledge + "&sort=stars";
            try{
                res.addAll(getProjects(fullUrl));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return res;
    }
    private static List<Project> parse(String jsonStr) {
       List<Project> res = new ArrayList<>();
        System.out.println(jsonStr);
        JSONObject obj = new JSONObject(jsonStr);
        JSONArray projects = obj.getJSONArray("items");
        for(int i = 0; i < projects.length(); i++) {
            Project temp = new Project();
            JSONObject project = projects.getJSONObject(i);

            temp.setIdProject(project.getLong("id"));
            temp.setName(project.getString("full_name"));
            temp.setLink(project.getString("html_url"));
            temp.setDescription(project.getString("description"));
            temp.setForks(project.getInt("forks_count"));
            temp.setStarGazers(project.getInt("stargazers_count"));

            temp.setHomePage(project.isNull("homepage")? null : project.getString("homepage"));
            temp.setReadme(project.getString("url")+"/readme");
            temp.setUpdateTime(toLocalDate(project.getString("updated_at")));
            res.add(temp);
        }
        return res;

    }

    public static LocalDate toLocalDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return LocalDate.parse(s, formatter);
    }
//    public static void main(String[] args) throws Exception{
//        List<String> stringList = new ArrayList<>();
//        stringList.add("java");
//        stringList.add("cloud");
//        stringList.add("markdown");
//        ProjectScrawler scrawler = new ProjectScrawler();
//        List<Project> res =runScrawler(stringList);
//        for(Project project: res) {
//            System.out.println(project.toString());
//        }
//    }
}
