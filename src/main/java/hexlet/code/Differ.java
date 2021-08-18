package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static hexlet.code.Parser.getMapFromFile;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {

        Map<String, Object> mapOfFile1 = getMapFromFile(filePath1);
        Map<String, Object> mapOfFile2 = getMapFromFile(filePath2);

        Set<String> keySet = new TreeSet<>();
        for (Map.Entry<String, Object> map : mapOfFile1.entrySet()) {
            keySet.add(map.getKey());
        }
        for (Map.Entry<String, Object> map : mapOfFile2.entrySet()) {
            keySet.add(map.getKey());
        }

        Map<String, Object> resultMap = new LinkedHashMap<>();

        for (String key : keySet) {
            if ((mapOfFile1.containsKey(key)) && (mapOfFile2.containsKey(key))) {
                if (mapOfFile1.get(key).equals(mapOfFile2.get(key))) {
                    resultMap.put("  " + key, mapOfFile2.get(key));
                } else {
                    resultMap.put("- " + key, mapOfFile1.get(key));
                    resultMap.put("+ " + key, mapOfFile2.get(key));
                }
            } else if ((mapOfFile1.containsKey(key)) && (!mapOfFile2.containsKey(key))) {
                resultMap.put("- " + key, mapOfFile1.get(key));
            } else {
                resultMap.put("+ " + key, mapOfFile2.get(key));
            }
        }

        return mapToString(resultMap);
    }

    public static <K, V> String mapToString(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\r\n", "{\r\n", "\r\n}"));
    }

}
