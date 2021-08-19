package hexlet.code;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Differ {

    public static final String UNCHANGED = "unchanged";
    public static final String CHANGED = "changed";
    public static final String ADDED = "added";
    public static final String REMOVED = "removed";

//    public static void main(String[] args) throws Exception {
//        String fileJson1 = "file1.json";
//        String fileJson2 = "file2.json";
//        System.out.println(generate(fileJson1, fileJson2, "stylish"));
//    }

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        return Formatter.getFormatter(formatName).getResult(filePath1, filePath2);
    }


    private static boolean checkNullValue(String key, Map<String, Object> map) {
        return map.get(key) == null;
    }

    public static Map<String, String> getDiffFile(Set<String> keySet,
                                                  Map<String, Object> map1,
                                                  Map<String, Object> map2) {
        Map<String, String> resultMapDiff = new LinkedHashMap<>();

        for (String key : keySet) {
            if ((map1.containsKey(key)) && (map2.containsKey(key))) {
                if (!checkNullValue(key, map1)) {
                    if (map1.get(key).equals(map2.get(key))) {
                        resultMapDiff.put(key, UNCHANGED);
                    } else {
                        resultMapDiff.put(key, CHANGED);
                    }
                } else {
                    if (map1.get(key) == map2.get(key)) {
                        resultMapDiff.put(key, UNCHANGED);
                    } else {
                        resultMapDiff.put(key, CHANGED);
                    }
                }
            } else if ((map1.containsKey(key)) && (!map2.containsKey(key))) {
                resultMapDiff.put(key, REMOVED);
            } else {
                resultMapDiff.put(key, ADDED);
            }
        }
        return resultMapDiff;
    }

}
