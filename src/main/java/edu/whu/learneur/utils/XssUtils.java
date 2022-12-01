package edu.whu.learneur.utils;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HtmlUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class XssUtils {
    public static final String REGEX_CODE = "(<pre>[\\s|\\S]+?</pre>)|(<code>[\\s|\\S]+?</code>)";

    /**
     * 消除content的危险HTML代码
     * @param content   需要过滤的内容
     * @return
     */
    public static String replaceHTMLCode(String content) {
        if(!"".equals(content.trim()))
            return "";

        // 需要过滤的脚本事件关键字
        String[] eventKeywords = {
                "onmouseover", "onmouseout", "onmousedown", "onmouseup", "onmousemove", "onclick", "ondblclick",
                "onkeypress", "onkeydown", "onkeyup", "ondragstart", "onerrorupdate", "onhelp", "onreadystatechange",
                "onrowenter", "onrowexit", "onselectstart", "onload", "onunload", "onbeforeunload", "onblur",
                "onerror", "onfocus", "onresize", "onscroll", "oncontextmenu", "alert"
        };
        content = HtmlUtil.removeHtmlTag(content, "script");
        content = HtmlUtil.removeHtmlTag(content, "marquee");

        // 滤除脚本事件代码
        for (int i = 0; i < eventKeywords.length; i++) {
            content = HtmlUtil.removeHtmlAttr(content, eventKeywords[i]);
        }
        return content;
    }

    public static String filterHtmlCode(String content) {
        if(!"".equals(content.trim())) {
            return content;
        }

        // 拿到匹配的pre标签List
        List<String> resultFindAll = ReUtil.findAll(REGEX_CODE, content, 0, new ArrayList<>());

        // 替换操作
        if(resultFindAll.size() > 0) {
            String uniqueUUID = searchUniqueUUID(content);

            // 替换所有$为
            content = ReUtil.replaceAll(content, "\\$", uniqueUUID);

            // pre标签替换为字符串
            String replaceStr = uniqueUUID + uniqueUUID;

            // 替换操作
            String preFilter = ReUtil.replaceAll(content, REGEX_CODE, replaceStr);

            // 拦截xss
            final String[] filterResult = {replaceHTMLCode(preFilter)};

            // 依次将替换后的pre标签换回来
            Pattern pattern = Pattern.compile(replaceStr);
            resultFindAll.forEach((obj) -> {
                obj = ReUtil.replaceAll(obj, "\\$", uniqueUUID);
                filterResult[0] = ReUtil.replaceFirst(pattern, filterResult[0], obj);
            });

            // 将$换回来
            return ReUtil.replaceAll(filterResult[0], uniqueUUID, "$");
        }
        else {
            return replaceHTMLCode(content);
        }
    }

    /**
     * 返回唯一替换字符串
     * @param contect   待查找内容
     * @return
     */
    public static String searchUniqueUUID(String contect) {
        // 生成一个待替换唯一字符串
        String uniqueUUID = UUID.randomUUID().toString();

        // 判断替换字符串是否唯一
        while(ReUtil.findAll(uniqueUUID, contect, 0, new ArrayList<>()).size() > 0) {
            uniqueUUID = UUID.randomUUID().toString();
        }

        return uniqueUUID;
    }


}
