package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Parser;

import static hexlet.code.Differ.LINE_SEPARATOR;
import static hexlet.code.formatters.Plain.isString;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public final class Json implements Format {

    @Override
    public String format(String fileName1, String fileName2) throws IOException {
        Map<String, Object> mapOfFile1 = Parser.getMapFromFile(fileName1);
        Map<String, Object> mapOfFile2 = Parser.getMapFromFile(fileName2);
        Set<String> keySet = Parser.getKeySet(mapOfFile1, mapOfFile2);

        Map<String, String> diff = Differ.getDiffFile(keySet, mapOfFile1, mapOfFile2);
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : diff.entrySet()) {
            String key = entry.getKey();
            builder.append(getStringBlock(key, mapOfFile1, mapOfFile2));
        }
//        builder.deleteCharAt(builder.lastIndexOf(",")).deleteCharAt(builder.lastIndexOf("\n"));
        builder.deleteCharAt(builder.lastIndexOf("\n"));
        return builder.toString().replaceAll("\r", "");
    }

    private static StringBuilder getStringBlock(String key, Map<String, Object> map1, Map<String, Object> map2) {
        return new StringBuilder("{").append(LINE_SEPARATOR)
                .append("  \"field\": ").append("\"").append(key).append("\",").append(LINE_SEPARATOR)
                .append("  \"was\": ")
                .append(isString(map1.get(key)) ? "\"" + map1.get(key) + "\"" : map1.get(key))
                .append(",").append(LINE_SEPARATOR)
                .append("  \"now\": ")
                .append(isString(map2.get(key)) ? "\"" + map2.get(key) + "\"" : map2.get(key))
                .append(",").append(LINE_SEPARATOR)
                .append("}").append(LINE_SEPARATOR);
    }
}
