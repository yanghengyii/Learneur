package edu.whu.learneur.crawler.Itheima;

import edu.whu.learneur.crawler.entity.Lesson;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IteimaSearch extends RequestConfig {


    public static List<Lesson> iteimaSearch(List<String> words) throws Exception {
        List<Lesson> infos = new ArrayList<>();
        String url = "http://yun.itheima.com";
        String word = "Java";
        String[] type = {"course","open","jishu"};
        String realhash = "18fc53a52a033fe75f7d0542124ca8c0_03f2050563f2638dda0b8986ab13a61d";

        //创建连接池管理器
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

        //设置连接数
        connectionManager.setMaxTotal(100);

        connectionManager.setDefaultMaxPerRoute(20);

        String content = null;
        for(int m=0;m< words.size();m++) {
            for (int k = 0; k < type.length; k++) {
                content = doGet(connectionManager, words.get(m), type[k], realhash);
                Document doc = Jsoup.parse(content);
                Elements element1 = doc.select("div.main");
                Elements lists = element1.first().select("li");
                for (int i = 0; i < lists.size(); i++) {
                    Lesson info = new Lesson();
                    String link = lists.get(i).select("a").attr("href");
                    String photoLink = url+lists.get(i).select("img[class=mask_img1]").attr("src");
                    System.out.println(photoLink);
                    String finalLink = url + link;
                    String title = lists.get(i).select("h2").text();
                    String summary = lists.get(i).select("p[class=p1]").text();
                    info.setImgPath(photoLink);
                    info.setLink(finalLink);
                    info.setTitle(title);
                    info.setDescription(summary);

                    infos.add(info);



                }
            }
        }
        return infos;
    }

    private static String doGet(PoolingHttpClientConnectionManager connectionManager,String word,String type,String realhash) throws IOException {
        //不是每次创建新的httpclient，而是从连接池中获取httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

        String content = null;

        HttpPost httpPost = new HttpPost("http://yun.itheima.com/search");

        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36 Vivaldi/1.1.453.52");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("so",word));
        params.add(new BasicNameValuePair("type",type));
        params.add(new BasicNameValuePair("realhash",realhash));

        //创建表单Entity对象
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"utf8");

        //设置表单的Entity对象到Post请求
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);

        if(response.getStatusLine().getStatusCode() == 200){
            content = EntityUtils.toString(response.getEntity(),"utf8");
            //System.out.println("--------"+ EntityUtils.toString(response.getEntity(),"utf8").length());
        }
        response.close();
        return content;
    }

}
