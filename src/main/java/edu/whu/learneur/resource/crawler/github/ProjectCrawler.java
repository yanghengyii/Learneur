package edu.whu.learneur.resource.crawler.github;

import edu.whu.learneur.resource.crawler.Crawler;
import edu.whu.learneur.resource.entity.Project;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectCrawler implements Crawler<Project> {
    private String url="https://api.github.com/search/repositories?q=";

    public String getResponse(String url) throws Exception {
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

        return responseStr;

    }
    public List<Project> crawl(String key) {
        List<Project> res = new ArrayList<>();
        String fullUrl;
        for(int i = 0; i < 30; i++){
            fullUrl = url + key + "&sort=stars&page="+i+"&per_page=30";
            try{
                String response = getResponse(fullUrl);
                if(response != null) {
                    res.addAll(parse(response));
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return res;
    }
    public List<Project> parse(String jsonStr) {
       List<Project> res = new ArrayList<>();
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
            temp.setLanguage(project.isNull("language")? null : project.getString("language"));
            temp.setHomePage(project.isNull("homepage")? null : project.getString("homepage"));
            temp.setReadme(project.getString("url")+"/readme");
            temp.setUpdateTime(toLocalDate(project.getString("updated_at")));
            res.add(temp);
        }
        return res;

    }

    public LocalDate toLocalDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return LocalDate.parse(s, formatter);
    }

    public static void main(String[] args) {
        ProjectCrawler crawler = new ProjectCrawler();
        List<Project> res = crawler.crawl("java");
        for(Project p : res) {
            System.out.println(p);
        }
        System.out.println(res.size());

    }
}
