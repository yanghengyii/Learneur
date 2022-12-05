package edu.whu.learneur.resource.crawler;

import java.util.List;

public interface Crawler<T>{
    List<T> crawl(String key) throws Exception;
//    String getResponse(String url) throws Exception;
//    List<T> parse(String jsonString);
}
