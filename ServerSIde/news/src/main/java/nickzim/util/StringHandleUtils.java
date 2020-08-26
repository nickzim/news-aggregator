package nickzim.util;

public class StringHandleUtils {


    public static String deleteTags(String str){
        return str.replaceAll("\\w*>|</\\w*","");
    }

    public static String deleteQuotes(String str){
        return str.replaceAll("&quot;","");
    }

    public static String deleteCDATAs(String str){
        return str.replaceAll("<!\\[CDATA\\[|]]","");
    }
}
