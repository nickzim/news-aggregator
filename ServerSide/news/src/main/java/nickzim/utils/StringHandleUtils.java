package nickzim.utils;

public final class StringHandleUtils {


    private static String deleteTags(String str){
        return str.replaceAll("<\\w+>|</\\w+>","");
    }

    private static String deleteQuotes(String str){
        return str.replaceAll("&quot;","");
    }

    private static String deleteCDATAs(String str){
        return str.replaceAll("<!\\[CDATA\\[|]]>","");
    }

    public static String handleString(String str){
        return deleteTags(deleteQuotes(deleteCDATAs(str)));
    }
}
