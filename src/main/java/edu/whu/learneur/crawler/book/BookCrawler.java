package edu.whu.learneur.crawler.book;

import edu.whu.learneur.crawler.Crawler;
import edu.whu.learneur.crawler.entity.Book;

import java.util.List;

public class BookCrawler implements Crawler<Book> {
    static String url = "https://z-lib.is/s?q=";

    @Override
    public List<Book> crawl(String key) {
        return null;
    }

    @Override
    public String getResponse(String url) throws Exception {
        return null;
    }

    @Override
    public List<Book> parse(String jsonString) {
        return null;
    }


    public static List<Book> getBooks(List<String> keywords) throws IOException {
        List<Book> books = new ArrayList<>(30);
        String keyword = String.join("+", keywords);
        String fullUrl = url + keyword;
        Document doc = Jsoup.parse(new URL(fullUrl), 30000);
        Element searchResultBox = doc.getElementById("searchResultBox");
        assert searchResultBox != null;
        List<Element> booksNode = searchResultBox.getElementsByClass("resItemBox");
        for (Element bookNode : booksNode) {
            books.add(getBook(bookNode));
        }
        return books;
    }


    private static Book getBook(Element node) {
        Book book = new Book();

        // get cover url
        Element titleNode = node.getElementsByClass("cover").get(0);
        String picUrl = (titleNode.attr("data-src").toString());
        book.setCoverUrl(picUrl);

        // get title
        String name = node.getElementsByAttributeValue("itemprop","name").get(0).child(0).childNodes().get(0).toString();
        book.setTitle(name);

        // get author
        String author = node.getElementsByAttributeValue("itemprop","author").get(0).childNode(0).toString();
        book.setAuthor(author);

        // get publisher
        String publisher = "";
        try {
            publisher = node.getElementsByAttributeValue("itemprop","publisher").get(0).childNode(0).toString();
        }catch (IndexOutOfBoundsException e){
        }
        book.setPublisher(publisher);

        // get year
        Elements properties = node.getElementsByClass("bookProperty");

        Element year = properties.get(0);
        String yearStr = year.childNode(3).childNode(0).toString();
        book.setPublishDate(yearStr);

        Element language = properties.get(1);
        String languageStr = "";
        try {
            languageStr = language.childNode(3).childNode(0).toString();
        } catch (IndexOutOfBoundsException e) {
        }
        book.setLanguage(languageStr);


        Element file = properties.get(2);
        String fileSize = "";
        String fileType = "";
        try {
            fileType = file.childNode(1).childNode(0).childNode(0).toString();
            fileSize = file.childNode(3).childNode(0).childNode(0).toString();
        }catch (IndexOutOfBoundsException e) {
            String[] attrs = file.childNode(3).childNode(0).toString().trim().split(", ");
            fileType = attrs[0];
            fileSize = attrs[1];
        }

        book.setFileType(fileType);
        book.setFileSize(fileSize);

        String[] tags = {};
        try {
            tags = properties.get(3).childNode(3).childNode(0).toString().split("; ");
        }catch (IndexOutOfBoundsException e) {
        }
        book.setTags(tags);

        return book;
    }

}
