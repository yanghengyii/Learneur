package edu.whu.learneur.crawler;

import java.util.List;

public interface Crawler<T>{
    List<T> crawl(String key);
    String getResponse(String url) throws Exception;
    List<T> parse(String jsonString);
}
