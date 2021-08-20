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
import static hexlet.code.Differ.LINE_SEPARATOR;
import static hexlet.code.Parser.getMapFromFile;

public final class Plain implements Format {

    @Override
    public String format(String fileName1, String fileName2) throws IOException {
        Map<String, Object> mapOfFile1 = getMapFromFile(fileName1);
        Map<String, Object> mapOfFile2 = getMapFromFile(fileName2);
        Set<String> keySet = Parser.getKeySet(mapOfFile1, mapOfFile2);

        Map<String, String> diff = Differ.getDiffFile(keySet, mapOfFile1, mapOfFile2);
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : diff.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();

            if (CHANGED.equals(difference)) {
                builder.append(change(key, mapOfFile1, mapOfFile2));
            } else if (REMOVED.equals(difference)) {
                builder.append(remove(key));
            } else if (ADDED.equals(difference)) {
                builder.append(add(key, mapOfFile2));
            }
        }
        builder.deleteCharAt(builder.lastIndexOf("\n"));
        return builder.toString().replaceAll("\r", "");
    }

    private static boolean isComplex(Object obj) {
        return obj instanceof List || obj instanceof Map || obj instanceof Object[];
    }

    public static boolean isString(Object obj) {
        return obj instanceof String;
    }

    private static StringBuilder change(String key, Map<String, Object> map1, Map<String, Object> map2) {
        return new StringBuilder("Property '")
                .append(key)
                .append("' was updated. From ")
                .append(isComplex(map1.get(key)) ? "[complex value]"
                        : isString(map1.get(key)) ? "'" + map1.get(key) + "'" : map1.get(key))
                .append(" to ")
                .append(isComplex(map2.get(key)) ? "[complex value]"
                        : isString(map2.get(key)) ? "'" + map2.get(key) + "'" : map2.get(key))
                .append(LINE_SEPARATOR);
    }

    private static StringBuilder remove(String key) {
        return new StringBuilder("Property '")
                .append(key)
                .append("' was removed")
                .append(LINE_SEPARATOR);
    }

    private static StringBuilder add(String key, Map<String, Object> map2) {
        return new StringBuilder("Property '")
                .append(key)
                .append("' was added with value: ")
                .append(isComplex(map2.get(key)) ? "[complex value]"
                        : isString(map2.get(key)) ? "'" + map2.get(key) + "'" : map2.get(key))
                .append(LINE_SEPARATOR);
    }
}
