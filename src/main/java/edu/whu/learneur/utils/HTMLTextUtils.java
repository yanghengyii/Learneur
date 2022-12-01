package edu.whu.learneur.utils;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.*;

public class HTMLTextUtils extends HTMLEditorKit.ParserCallback {
    private static HTMLTextUtils htmlTextUtils = new HTMLTextUtils();

    private StringBuffer buffer;

    public HTMLTextUtils(){};

    public void parse(String html) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(html.getBytes());
        Reader reader = new InputStreamReader(inputStream);
        ParserDelegator delegator = new ParserDelegator();
        buffer = new StringBuffer();
        delegator.parse(reader, this, Boolean.TRUE);
    }

    @Override
    public void handleText(char[] data, int pos) {
        buffer.append(data);
    }

    public String getText() {
        return buffer.toString();
    }

    public static String getContent(String str) {
        try {
            htmlTextUtils.parse(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlTextUtils.getText();
    }
}
