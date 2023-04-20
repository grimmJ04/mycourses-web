package hu.szte.inf.utils;

public class ViewResolver {

    private static String prefix = "";
    private static String postfix = "";

    public static String resolve(String viewName) {
        return prefix + viewName + postfix;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static synchronized void setPrefix(String prefix) {
        ViewResolver.prefix = prefix;
    }

    public static String getPostfix() {
        return postfix;
    }

    public static synchronized void setPostfix(String postfix) {
        ViewResolver.postfix = postfix;
    }
}
