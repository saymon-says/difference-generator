package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static hexlet.code.Parser.getMapFromFile;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {

        Map<String, Object> mapOfFile1 = getMapFromFile(filePath1);
        Map<String, Object> mapOfFile2 = getMapFromFile(filePath2);

        Set<String> keySet = Parser.getKeySet(mapOfFile1, mapOfFile2);

        Map<String, Object> resultMap = new LinkedHashMap<>();

        for (String key : keySet) {
            if ((mapOfFile1.containsKey(key)) && (mapOfFile2.containsKey(key))) {
                if (!checkNullValue(key, mapOfFile1)) {
                    if (mapOfFile1.get(key).equals(mapOfFile2.get(key))) {
                        resultMap.put("  " + key, mapOfFile2.get(key));
                    } else {
                        resultMap.put("- " + key, mapOfFile1.get(key));
                        resultMap.put("+ " + key, mapOfFile2.get(key));
                    }
                } else {
                    if (mapOfFile1.get(key) == mapOfFile2.get(key)) {
                        resultMap.put("  " + key, mapOfFile2.get(key));
                    } else {
                        resultMap.put("- " + key, mapOfFile1.get(key));
                        resultMap.put("+ " + key, mapOfFile2.get(key));
                    }
                }
            } else if ((mapOfFile1.containsKey(key)) && (!mapOfFile2.containsKey(key))) {
                resultMap.put("- " + key, mapOfFile1.get(key));
            } else {
                resultMap.put("+ " + key, mapOfFile2.get(key));
            }
        }

        return mapToString(resultMap);
    }

    private static boolean checkNullValue(String key, Map<String, Object> map) {
        return map.get(key) == null;
    }

    public static <K, V> String mapToString(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\r\n", "{\r\n", "\r\n}"));
    }

}
