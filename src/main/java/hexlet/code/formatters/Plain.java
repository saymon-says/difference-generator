package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static hexlet.code.Differ.*;
import static hexlet.code.Parser.getMapFromFile;

public class Plain {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static String getResultPlainMap(String fileName1, String fileName2) throws IOException {

        Map<String, Object> mapOfFile1 = getMapFromFile(fileName1);
        Map<String, Object> mapOfFile2 = getMapFromFile(fileName2);
        Set<String> keySet = Parser.getKeySet(mapOfFile1, mapOfFile2);

        Map<String, String> diff = Differ.getDiffFile(keySet, mapOfFile1, mapOfFile2);
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : diff.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();

            if (difference.equals(CHANGED)) {
                builder.append("Property '")
                        .append(key)
                        .append("' was updated. From ")
                        .append(isComplex(mapOfFile1.get(key)) ? "[complex value]"
                                : isString(mapOfFile1.get(key)) ? "'" + mapOfFile1.get(key) + "'" : mapOfFile1.get(key))
                        .append(" to ")
                        .append(isComplex(mapOfFile2.get(key)) ? "[complex value]"
                                : isString(mapOfFile2.get(key)) ? "'" + mapOfFile2.get(key) + "'" : mapOfFile2.get(key))
                        .append(LINE_SEPARATOR);
            }
            if (difference.equals(REMOVED)) {
                builder.append("Property '")
                        .append(key)
                        .append("' was removed")
                        .append(LINE_SEPARATOR);
            }
            if (difference.equals(ADDED)) {
                builder.append("Property '")
                        .append(key)
                        .append("' was added with value: ")
                        .append(isComplex(mapOfFile2.get(key)) ? "[complex value]"
                                : isString(mapOfFile2.get(key)) ? "'" + mapOfFile2.get(key) + "'" : mapOfFile2.get(key))
                        .append(LINE_SEPARATOR);
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

}
