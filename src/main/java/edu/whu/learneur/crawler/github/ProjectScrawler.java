package edu.whu.learneur.crawler.github;

import edu.whu.learneur.crawler.entity.Project;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProjectScrawler {
    static String url="https://api.github.com/repos/";

    private static List<Project> getProjects(String url) throws  Exception {
        URL obj = new URL(url);

    }
    private static void runScrawler(List<String> knowledges) throws Exception {
        for(String knowledge : knowledges) {

        }
    }
    private static List<Project> prase(String jsonStr) {
        List<Project> projects = new ArrayList<>();
        JSONObject obj = new JSONObject(jsonStr);

    }
}
