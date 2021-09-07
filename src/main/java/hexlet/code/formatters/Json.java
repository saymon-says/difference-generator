package hexlet.code.formatters;

import static hexlet.code.Differ.LINE_SEPARATOR;
import static hexlet.code.formatters.Plain.isString;

import java.util.Map;

public final class Json implements Format {

    @Override
    public String format(Map<String, String> differenceMap,
                         Map<String, Object> firstContent,
                         Map<String, Object> secondContent) {

        StringBuilder builder = new StringBuilder();
        for (String key : differenceMap.keySet()) {
            builder.append(getStringBlock(key, firstContent, secondContent));
        }
        builder.deleteCharAt(builder.lastIndexOf(",")).deleteCharAt(builder.lastIndexOf("\n"));
        return builder.toString().replaceAll("\r", "");
    }

    private static StringBuilder getStringBlock(String key,
                                                Map<String, Object> firstContent,
                                                Map<String, Object> secondContent) {
        return new StringBuilder("{").append(LINE_SEPARATOR)
                .append("  \"field\": ").append("\"").append(key).append("\",").append(LINE_SEPARATOR)
                .append("  \"was\": ")
                .append(isString(firstContent.get(key)) ? "\""
                        + firstContent.get(key) + "\"" : firstContent.get(key))
                .append(",").append(LINE_SEPARATOR)
                .append("  \"now\": ")
                .append(isString(secondContent.get(key)) ? "\""
                        + secondContent.get(key) + "\"" : secondContent.get(key))
                .append(LINE_SEPARATOR).append("}").append(",").append(LINE_SEPARATOR);
    }
}
