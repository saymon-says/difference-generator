package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Parser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static hexlet.code.Differ.*;
import static hexlet.code.Parser.getMapFromFile;

public class Stylish {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static Map<String, Object> getResultStylishMap(String fileName1, String fileName2) throws IOException {

        Map<String, Object> mapOfFile1 = getMapFromFile(fileName1);
        Map<String, Object> mapOfFile2 = getMapFromFile(fileName2);
        Set<String> keySet = Parser.getKeySet(mapOfFile1, mapOfFile2);

        Map<String, Object> resultGenerate = new LinkedHashMap<>();
        Map<String, String> diff = Differ.getDiffFile(keySet, mapOfFile1, mapOfFile2);
        for (Map.Entry<String, String> entry : diff.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();
            if (difference.equals(UNCHANGED)) {
                resultGenerate.put("  " + key, mapOfFile1.get(key));
            }
            if (difference.equals(CHANGED)) {
                resultGenerate.put("- " + key, mapOfFile1.get(key));
                resultGenerate.put("+ " + key, mapOfFile2.get(key));
            }
            if (difference.equals(REMOVED)) {
                resultGenerate.put("- " + key, mapOfFile1.get(key));
            }
            if (difference.equals(ADDED)) {
                resultGenerate.put("+ " + key, mapOfFile2.get(key));
            }
        }
        return resultGenerate;
    }

    public static <K, V> String mapToString(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(LINE_SEPARATOR, "{" + LINE_SEPARATOR, LINE_SEPARATOR + "}"));
    }

}
