package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static final String UNCHANGED = "unchanged";
    public static final String CHANGED = "changed";
    public static final String ADDED = "added";
    public static final String REMOVED = "removed";
    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Map<String, Object> mapOfFile1 = Parser.getMapFromFile(getFile(filePath1), filePath1);
        Map<String, Object> mapOfFile2 = Parser.getMapFromFile(getFile(filePath2), filePath2);
        Set<String> keySet = getKeySet(mapOfFile1, mapOfFile2);
        Map<String, String> diff = Differ.getDiffFile(keySet, mapOfFile1, mapOfFile2);
        return Formatter.getFormatter(formatName).format(diff, mapOfFile1, mapOfFile2);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
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

    public static Set<String> getKeySet(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keySet = new TreeSet<>();
        for (Map.Entry<String, Object> map : map1.entrySet()) {
            keySet.add(map.getKey());
        }
        for (Map.Entry<String, Object> map : map2.entrySet()) {
            keySet.add(map.getKey());
        }
        return keySet;
    }

    public static Path getFixturePath(String fileName) {
        return Paths.get(fileName).toAbsolutePath().normalize();
    }

    public static String getFile(String fileName) throws IOException {
        return Files.readString(getFixturePath(fileName));
    }


}
