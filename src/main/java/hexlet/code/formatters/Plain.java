package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import static hexlet.code.Differ.CHANGED;
import static hexlet.code.Differ.REMOVED;
import static hexlet.code.Differ.ADDED;
import static hexlet.code.Differ.LINE_SEPARATOR;

public final class Plain implements Format {

    @Override
    public String format(Map<String, String> diffOfFiles,
                         Map<String, Object> fileName1,
                         Map<String, Object> fileName2) {

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : diffOfFiles.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();

            if (CHANGED.equals(difference)) {
                builder.append(change(key, fileName1, fileName2));
            } else if (REMOVED.equals(difference)) {
                builder.append(remove(key));
            } else if (ADDED.equals(difference)) {
                builder.append(add(key, fileName2));
            }
        }
        builder.deleteCharAt(builder.lastIndexOf("\n"));
        return builder.toString().replaceAll("\r", "");
    }

    private static boolean isComplex(Object obj) {
        return obj instanceof List || obj instanceof Map || obj instanceof Object[];
    }

    public static boolean isString(Object obj) {
        return obj instanceof String;
    }

    private static StringBuilder change(String key, Map<String, Object> map1, Map<String, Object> map2) {
        return new StringBuilder("Property '")
                .append(key)
                .append("' was updated. From ")
                .append(isComplex(map1.get(key)) ? "[complex value]"
                        : isString(map1.get(key)) ? "'" + map1.get(key) + "'" : map1.get(key))
                .append(" to ")
                .append(isComplex(map2.get(key)) ? "[complex value]"
                        : isString(map2.get(key)) ? "'" + map2.get(key) + "'" : map2.get(key))
                .append(LINE_SEPARATOR);
    }

    private static StringBuilder remove(String key) {
        return new StringBuilder("Property '")
                .append(key)
                .append("' was removed")
                .append(LINE_SEPARATOR);
    }

    private static StringBuilder add(String key, Map<String, Object> map2) {
        return new StringBuilder("Property '")
                .append(key)
                .append("' was added with value: ")
                .append(isComplex(map2.get(key)) ? "[complex value]"
                        : isString(map2.get(key)) ? "'" + map2.get(key) + "'" : map2.get(key))
                .append(LINE_SEPARATOR);
    }
}
