package edu.whu.learneur.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

/**
 * 异常处理工具类
 */
public class ExceptionsUtils {
    /**
     * 将checked exception转换为unchecked exception
     * @param e exception
     * @return unchecked exception
     */
    public static RuntimeException unchecked(Exception e) {
        if(e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 将ErrorStack转换为String
     * @param e throwable object
     * @return error strings
     */
    public static String getStackTraceAsString(Throwable e) {
        if(Objects.isNull(e)) {
            return "";
        }
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    /**
     * 判断异常是否由某些低层异常引起
     * @param e             异常
     * @param causeClasses  异常原因
     * @return
     */
    public static boolean isCausedBy(Exception e, Class<? extends Exception>... causeClasses) {
        Throwable cause = e.getCause();
        while (Objects.nonNull(cause)) {
            for (Class<? extends Exception> causeClass : causeClasses) {
                if(causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }

    /**
     * 从request获取异常类
     * @param request  http request
     * @return
     */
    public static Throwable getThrowable(HttpServletRequest request) {
        Throwable e = null;
        if(Objects.nonNull(request.getAttribute("exception"))) {
            e = (Throwable) request.getAttribute("exception");
        } else if (Objects.nonNull(request.getAttribute("javax.servlet.error.exception"))) {
            e = (Throwable) request.getAttribute("javax.servlet.error.exception");
        }
        return e;
    }
}
