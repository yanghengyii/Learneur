package edu.whu.learneur.resource.crawler.bilibili;

import edu.whu.learneur.resource.crawler.Crawler;
import edu.whu.learneur.resource.entity.Video;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

// bilibili scrwaler for video
@Component
public class VideoCrawler implements Crawler<Video> {
    static String url = "https://api.bilibili.com/x/space/arc/search?mid=%d&ps=%d&pn=%d&keyword=%s&order=pubdate&jsonp=jsonp";
    static int[] users = {
            95256449, // 遇见狂神说
            12890453, // 程序员鱼皮
            302417610, // 尚硅谷
            76542346, // 动力节点
            651245581, //韩顺平
            37974444, // 黑马程序员
    };
    static int pageSize = 30;

    private static String getUrlString(int mid,int pageSize,int pageNum,String keyword) {
        return String.format(url, mid, pageSize, pageNum, keyword);
    }


    /*
        public static List<Video> getVideos(String keyword) throws IOException { // use multi-thread processing
            List<Video> videos = new ArrayList<>(100);
            List<CompletableFuture<List<Video>>> futures = new ArrayList<>(users.length);

            for (int user : users) {
                String fullUrl = getUrlString(user, pageSize, 1, keyword);
                CompletableFuture<List<Video>> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        return getVideosFromUser(fullUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
                futures.add(future);
            }

            CompletableFuture<Void> allDone = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
            allDone.join();
            for (CompletableFuture<List<Video>> future : futures) {
                videos.addAll(future.join());
            }
            return videos;
        }

        private static List<Video> getVideosFromUser(String url) throws IOException {


            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                System.out.println("GET request not worked");
                return null;
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String responseStr = response.toString();

            return parseVideo(responseStr);
        }

        private static String getUrlString(int mid,int pageSize,int pageNum,String keyword) {
            return String.format(url, mid, pageSize, pageNum, keyword);
        }

        private static List<Video> parseVideo(String jsonString) {
            List<Video> videos = new ArrayList<>(30);
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject pageInfo = data.getJSONObject("page");
            JSONObject list = data.getJSONObject("list");
            JSONArray videoList = list.getJSONArray("vlist");
            for (int i = 0; i < videoList.length(); i++) {
                Video temp = new Video();
                JSONObject video = videoList.getJSONObject(i);
                temp.setBVid(video.getString("bvid"));
                temp.setAuthor(video.getString("author"));
                temp.setDescription(video.getString("description"));
                temp.setLength(video.getString("length"));
                temp.setPic(video.getString("pic"));
                temp.setTitle(video.getString("title"));
                videos.add(temp);
            }
            return videos;
        }
         */
    @Override
    public List<Video> crawl(String key) {
        List<Video> videos = new ArrayList<>(100);
        List<CompletableFuture<List<Video>>> futures = new ArrayList<>(users.length);

        for (int user : users) {
            String fullUrl = getUrlString(user, pageSize, 1, key);
            CompletableFuture<List<Video>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return parse(getResponse(fullUrl));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            });
            futures.add(future);
        }

        CompletableFuture<Void> allDone = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        allDone.join();
        for (CompletableFuture<List<Video>> future : futures) {
            videos.addAll(future.join());
        }
        return videos;
    }

    public String getResponse(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            System.out.println("GET request not worked");
            return null;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public List<Video> parse(String jsonString) {
        List<Video> videos = new ArrayList<>(30);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONObject pageInfo = data.getJSONObject("page");
        JSONObject list = data.getJSONObject("list");
        JSONArray videoList = list.getJSONArray("vlist");
        for (int i = 0; i < videoList.length(); i++) {
            Video temp = new Video();
            JSONObject video = videoList.getJSONObject(i);
            temp.setBVid(video.getString("bvid"));
            temp.setAuthor(video.getString("author"));
            temp.setDescription(video.getString("description"));
            temp.setLength(video.getString("length"));
            temp.setPic(video.getString("pic"));
            temp.setTitle(video.getString("title"));
            videos.add(temp);
        }
        return videos;
    }
}
