package hexlet.code.formatters;

import static hexlet.code.Differ.LINE_SEPARATOR;
import static hexlet.code.formatters.Plain.isString;

import java.util.Map;

public final class Json implements Format {

    @Override
    public String format(Map<String, String> diffOfFiles,
                         Map<String, Object> fileName1,
                         Map<String, Object> fileName2) {

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : diffOfFiles.entrySet()) {
            String key = entry.getKey();
            builder.append(getStringBlock(key, fileName1, fileName2));
        }
        builder.deleteCharAt(builder.lastIndexOf(",")).deleteCharAt(builder.lastIndexOf("\n"));
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
                .append(LINE_SEPARATOR).append("}").append(",").append(LINE_SEPARATOR);
    }
}
