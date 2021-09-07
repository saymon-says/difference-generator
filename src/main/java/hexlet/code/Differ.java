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
        Map<String, Object> mapOfFile1 = Parser.parseToMap(getContentFile(filePath1), filePath1);
        Map<String, Object> mapOfFile2 = Parser.parseToMap(getContentFile(filePath2), filePath2);
        Set<String> keySet = getKeySet(mapOfFile1, mapOfFile2);
        Map<String, String> diff = buildDiff(keySet, mapOfFile1, mapOfFile2);
        return Formatter.getFormatter(formatName).format(diff, mapOfFile1, mapOfFile2);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static Map<String, String> buildDiff(Set<String> keySet,
                                                Map<String, Object> map1,
                                                Map<String, Object> map2) {
        Map<String, String> resultMapDiff = new LinkedHashMap<>();

        for (String key : keySet) {
            if ((map1.containsKey(key)) && (map2.containsKey(key))) {
                if (map1.get(key) != null) {
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
        Set<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(map2.keySet());

        return keySet;
    }

    public static Path getNormalizedPath(String fileName) {
        return Paths.get(fileName).toAbsolutePath().normalize();
    }

    public static String getContentFile(String fileName) throws IOException {
        return Files.readString(getNormalizedPath(fileName));
    }


}
