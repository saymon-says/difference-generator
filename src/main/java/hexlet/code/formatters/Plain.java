package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Parser;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static hexlet.code.Differ.*;
import static hexlet.code.Parser.getMapFromFile;

public class Plain {

    private static final String LINE_SEPARATOR = System.lineSeparator();


    public static Map<String, Object> getResultPlainMap(String fileName1, String fileName2) throws IOException {

        Map<String, Object> mapOfFile1 = getMapFromFile(fileName1);
        Map<String, Object> mapOfFile2 = getMapFromFile(fileName2);
        Set<String> keySet = Parser.getKeySet(mapOfFile1, mapOfFile2);

        Map<String, Object> resultGenerate = new LinkedHashMap<>();
        Map<String, String> diff = Differ.getDiffFile(keySet, mapOfFile1, mapOfFile2);
        for (Map.Entry<String, String> entry : diff.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();

            if (difference.equals(CHANGED)) {
                resultGenerate.put(key, Arrays.asList(mapOfFile1.get(key), mapOfFile2.get(key)));
            }
            if (difference.equals(REMOVED)) {
                resultGenerate.put(key, List.of(""));
            }
            if (difference.equals(ADDED)) {
                resultGenerate.put(key, List.of(mapOfFile2.get(key)));
            }
        }
        return resultGenerate;
    }

    public static String plain(Map<String, Object> map) {
        return null;


    }


}
