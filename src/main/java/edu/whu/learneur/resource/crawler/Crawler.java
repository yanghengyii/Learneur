package edu.whu.learneur.resource.crawler;

import java.util.List;

public interface Crawler<T> extends Runnable{
    List<T> crawl(String key) throws Exception;
    @Override
    void run();

    //    String getResponse(String url) throws Exception;
//    List<T> parse(String jsonString);
}
