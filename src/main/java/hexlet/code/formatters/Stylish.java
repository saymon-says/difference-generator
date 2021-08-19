package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Parser;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static hexlet.code.Differ.CHANGED;
import static hexlet.code.Differ.REMOVED;
import static hexlet.code.Differ.ADDED;
import static hexlet.code.Differ.UNCHANGED;

import static hexlet.code.Parser.getMapFromFile;

public class Stylish implements Formatter {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static final StringBuilder builder = new StringBuilder("{" + LINE_SEPARATOR);

    public static String getResultStylishMap(String fileName1, String fileName2) throws IOException {

        Map<String, Object> mapOfFile1 = getMapFromFile(fileName1);
        Map<String, Object> mapOfFile2 = getMapFromFile(fileName2);
        Set<String> keySet = Parser.getKeySet(mapOfFile1, mapOfFile2);

        Map<String, String> diff = Differ.getDiffFile(keySet, mapOfFile1, mapOfFile2);
        Stylish stylish = new Stylish();
        for (Map.Entry<String, String> entry : diff.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();
            if (difference.equals(UNCHANGED)) {
                stylish.unchanged(key, mapOfFile1, mapOfFile2);
            }
            if (difference.equals(CHANGED)) {
                stylish.changed(key, mapOfFile1, mapOfFile2);
            }
            if (difference.equals(REMOVED)) {
                stylish.removed(key, mapOfFile1, mapOfFile2);
            }
            if (difference.equals(ADDED)) {
                stylish.added(key, mapOfFile1, mapOfFile2);
            }
        }
        builder.append("}");
        return builder.toString();
    }


    @Override
    public String changed(String key, Map<String, Object> map1, Map<String, Object> map2) {
        return removed(key,map1, map2) + added(key, map1, map2);
    }

    @Override
    public String unchanged(String key, Map<String, Object> map1, Map<String, Object> map2) {
        builder.append("    ")
                .append(key)
                .append(": ")
                .append(map1.get(key))
                .append(LINE_SEPARATOR);
        return builder.toString();
    }

    @Override
    public String added(String key, Map<String, Object> map1, Map<String, Object> map2) {
        builder.append("  + ")
                .append(key)
                .append(": ")
                .append(map2.get(key))
                .append(LINE_SEPARATOR);
        return builder.toString();
    }

    @Override
    public String removed(String key, Map<String, Object> map1, Map<String, Object> map2) {
        builder.append("  - ")
                .append(key)
                .append(": ")
                .append(map1.get(key))
                .append(LINE_SEPARATOR);
        return builder.toString();
    }
}
