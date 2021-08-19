package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static hexlet.code.Differ.CHANGED;
import static hexlet.code.Differ.REMOVED;
import static hexlet.code.Differ.ADDED;

import static hexlet.code.Parser.getMapFromFile;

public class Plain implements Formatter {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static final StringBuilder builder = new StringBuilder();

    public static String getResultPlainMap(String fileName1, String fileName2) throws IOException {

        Map<String, Object> mapOfFile1 = getMapFromFile(fileName1);
        Map<String, Object> mapOfFile2 = getMapFromFile(fileName2);
        Set<String> keySet = Parser.getKeySet(mapOfFile1, mapOfFile2);

        Map<String, String> diff = Differ.getDiffFile(keySet, mapOfFile1, mapOfFile2);
        Plain plain = new Plain();
        for (Map.Entry<String, String> entry : diff.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();

            if (difference.equals(CHANGED)) {
                plain.changed(key, mapOfFile1, mapOfFile2);
            }
            if (difference.equals(REMOVED)) {
                plain.removed(key, mapOfFile1, mapOfFile2);
            }
            if (difference.equals(ADDED)) {
                plain.added(key, mapOfFile1, mapOfFile2);
            }
        }
        builder.deleteCharAt(builder.lastIndexOf("\r\n"));
        builder.deleteCharAt(builder.lastIndexOf("\n"));
        return builder.toString();
    }

    private static boolean isComplex(Object obj) {
        return obj instanceof List || obj instanceof Map || obj instanceof Object[];
    }

    private static boolean isString(Object obj) {
        return obj instanceof String;
    }

    @Override
    public String changed(String key, Map<String, Object> map1, Map<String, Object> map2) {
        builder.append("Property '")
                .append(key)
                .append("' was updated. From ")
                .append(isComplex(map1.get(key)) ? "[complex value]"
                        : isString(map1.get(key)) ? "'" + map1.get(key) + "'" : map1.get(key))
                .append(" to ")
                .append(isComplex(map2.get(key)) ? "[complex value]"
                        : isString(map2.get(key)) ? "'" + map2.get(key) + "'" : map2.get(key))
                .append(LINE_SEPARATOR);
        return builder.toString();
    }

    @Override
    public String unchanged(String key, Map<String, Object> map1, Map<String, Object> map2) {
        return null;
    }

    @Override
    public String added(String key, Map<String, Object> map1, Map<String, Object> map2) {
        builder.append("Property '")
                .append(key)
                .append("' was added with value: ")
                .append(isComplex(map2.get(key)) ? "[complex value]"
                        : isString(map2.get(key)) ? "'" + map2.get(key) + "'" : map2.get(key))
                .append(LINE_SEPARATOR);
        return builder.toString();
    }

    @Override
    public String removed(String key, Map<String, Object> map1, Map<String, Object> map2) {
        builder.append("Property '")
                .append(key)
                .append("' was removed")
                .append(LINE_SEPARATOR);
        return builder.toString();
    }
}
