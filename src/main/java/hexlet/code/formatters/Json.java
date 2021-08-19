package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Parser;

import static hexlet.code.Differ.CHANGED;
import static hexlet.code.Differ.UNCHANGED;
import static hexlet.code.Differ.REMOVED;
import static hexlet.code.Differ.ADDED;
import static hexlet.code.formatters.Plain.isString;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public final class Json implements Format {

    @Override
    public String getResult(String fileName1, String fileName2) throws IOException {
        Map<String, Object> mapOfFile1 = Parser.getMapFromFile(fileName1);
        Map<String, Object> mapOfFile2 = Parser.getMapFromFile(fileName2);
        Set<String> keySet = Parser.getKeySet(mapOfFile1, mapOfFile2);

        Map<String, String> diff = Differ.getDiffFile(keySet, mapOfFile1, mapOfFile2);
        StringBuilder builder = new StringBuilder("{\n");
        for (Map.Entry<String, String> entry : diff.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();

            if (difference.equals(CHANGED)) {
                builder.append("  \"- ").append(key).append('"').append(": ")
                        .append(mapOfFile1.get(key))
                        .append(",\n")
                        .append("  \"+ ").append(key).append('"').append(": ")
                        .append(mapOfFile2.get(key))
                        .append(",\n");
            }
            if (difference.equals(UNCHANGED)) {
                builder.append("  \"  ").append(key).append('"').append(": ")
                        .append(isString(mapOfFile1.get(key)) ? "\"" + mapOfFile1.get(key) + "\"" : mapOfFile1.get(key))
                        .append(",\n");
            }
            if (difference.equals(ADDED)) {
                builder.append("  \"+ ").append(key).append('"').append(": ")
                        .append(isString(mapOfFile2.get(key)) ? "\"" + mapOfFile2.get(key) + "\"" : mapOfFile2.get(key))
                        .append(",\n");
            }
            if (difference.equals(REMOVED)) {
                builder.append("  \"- ").append(key).append('"').append(": ")
                        .append(isString(mapOfFile1.get(key)) ? "\"" + mapOfFile1.get(key) + "\"" : mapOfFile1.get(key))
                        .append(",\n");
            }
        }
        builder.deleteCharAt(builder.lastIndexOf(",")).append("}");
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(builder.toString().replaceAll("\n", "").replaceAll("\"", "'"));
//        return builder.toString().replaceAll("\n", "").replaceAll("\"", "\"");
        return builder.toString();
    }
}
