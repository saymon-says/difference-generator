package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {

    private static final int SUB_STRING = 4;
//
//    public static void main(String[] args) throws IOException {
//        Path path1 = Paths.get("src/main/java/resource/filepath1.json");
//        Path path2 = Paths.get("src/main/java/resource/filepath2.json");
//        generate(path1, path2);
//    }

    public static String generate(Path filePath1, Path filePath2) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        File file1 = new File(String.valueOf(filePath1));
        File file2 = new File(String.valueOf(filePath2));
        Map<String, Object> mapOfFile1 = mapper.readValue(file1, new TypeReference<>() {
        });
        Map<String, Object> mapOfFile2 = mapper.readValue(file2, new TypeReference<>() {
        });

        Map<String, Object> resultMap = new HashMap<>();

        for (Map.Entry<String, Object> mapEntry : mapOfFile1.entrySet()) {
            if (mapOfFile2.containsKey(mapEntry.getKey())) {
                if (!mapEntry.getValue().equals(mapOfFile2.get(mapEntry.getKey()))) {
                    resultMap.put("- " + mapEntry.getKey(), mapEntry.getValue());
                    resultMap.put("+ " + mapEntry.getKey(), mapOfFile2.get(mapEntry.getKey()));
                } else {
                    resultMap.put("  " + mapEntry.getKey(), mapEntry.getValue());
                }
            } else {
                resultMap.put("- " + mapEntry.getKey(), mapEntry.getValue());
            }
        }

        for (Map.Entry<String, Object> mapEntry : mapOfFile2.entrySet()) {
            if (!mapOfFile1.containsKey(mapEntry.getKey())) {
//                if (!mapEntry.getValue().equals(mapOfFile1.get(mapEntry.getKey()))) {
//                    resultMap.put("- " + mapEntry.getKey(), mapEntry.getValue());
//                    resultMap.put("+ " + mapEntry.getKey(), mapOfFile1.get(mapEntry.getKey()));
//                } else {
//                    resultMap.put("  " + mapEntry.getKey(), mapEntry.getValue());
//                }
//            } else {
                resultMap.put("+ " + mapEntry.getKey(), mapEntry.getValue());
            }
        }
        return mapToString(resultMap);
    }

    public static <K, V> String mapToString(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
                .sorted((x1, x2) -> {
                    String withoutPrefix1 = x1.substring(SUB_STRING);
                    String withoutPrefix2 = x2.substring(SUB_STRING);
                    return CharSequence.compare(withoutPrefix1, withoutPrefix2);
                })
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

}
