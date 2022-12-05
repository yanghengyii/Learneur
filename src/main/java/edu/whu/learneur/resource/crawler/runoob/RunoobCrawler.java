package edu.whu.learneur.crawler.Runoob;

import edu.whu.learneur.crawler.Crawler;
import edu.whu.learneur.crawler.entity.Tutorial;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class RunoobCrawler extends RequestConfig implements Crawler<Tutorial> {


    public List<Tutorial> crawl(String key) throws Exception {
        List<Tutorial> infos = new ArrayList<>();
        String url = "https://www.runoob.com/";
        String word = "Java";
        int pages = 3;

        //创建连接池管理器
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

        //设置连接数
        connectionManager.setMaxTotal(100);

        connectionManager.setDefaultMaxPerRoute(20);

        String content = null;
        for(int j = 1;j<=pages;j++) {
            content = doGet(connectionManager, key,j);
            Document doc = Jsoup.parse(content);

            Elements element1 = doc.select("div.archive-list-item");
            for (int i = 0; i < element1.size(); i++) {
                Tutorial info = new Tutorial();
                String link = element1.get(i).select("a").attr("href");
                String finalLink = link;

                if(!finalLink.matches("http.*")){
                    continue;
                }

                String title = element1.get(i).select("a").attr("title").replaceAll("<em>","").replaceAll("</em>","").trim();


                String summary = element1.get(i).select("p").text();
                System.out.println(summary);
                info.setLink(finalLink);
                info.setName(title);
                info.setSummary(summary);
                infos.add(info);


            }
        }
        return infos;
    }

    private static String doGet(PoolingHttpClientConnectionManager connectionManager,String word,int page) throws IOException, URISyntaxException {
        //不是每次创建新的httpclient，而是从连接池中获取httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

        String content = null;

        URIBuilder uriBuilder = new URIBuilder("https://www.runoob.com/?");

        //设置参数
        uriBuilder.setParameter("s","Java");
        uriBuilder.setParameter("page",String.valueOf(page));

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36 Vivaldi/1.1.453.52");

        CloseableHttpResponse response = httpClient.execute(httpGet);


        if(response.getStatusLine().getStatusCode() == 200){
            content = EntityUtils.toString(response.getEntity(),"utf8");
            //System.out.println("--------"+ EntityUtils.toString(response.getEntity(),"utf8").length());
        }
        response.close();
        return content;
    }

}
