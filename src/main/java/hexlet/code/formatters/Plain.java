package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import static hexlet.code.Differ.CHANGED;
import static hexlet.code.Differ.REMOVED;
import static hexlet.code.Differ.ADDED;
import static hexlet.code.Differ.UNCHANGED;
import static hexlet.code.Differ.LINE_SEPARATOR;

public final class Plain implements Format {

    @Override
    public String format(Map<String, String> differenceMap,
                         Map<String, Object> firstContent,
                         Map<String, Object> secondContent) {

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();

            switch (difference) {
                case CHANGED -> builder.append(change(key, firstContent.get(key), secondContent.get(key)));
                case REMOVED -> builder.append(remove(key));
                case ADDED -> builder.append(add(key, secondContent.get(key)));
                case UNCHANGED -> {
                    break;
                }
                default -> throw new IllegalArgumentException();
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

    private static StringBuilder change(String key, Object objFirstContent, Object objSecondContent) {
        return new StringBuilder("Property '")
                .append(key).append("' was updated. From ")
                .append(toString(objFirstContent)).append(" to ")
                .append(toString(objSecondContent)).append(LINE_SEPARATOR);
    }

    private static StringBuilder remove(String key) {
        return new StringBuilder("Property '")
                .append(key)
                .append("' was removed")
                .append(LINE_SEPARATOR);
    }

    private static StringBuilder add(String key, Object obj) {
        return new StringBuilder("Property '")
                .append(key).append("' was added with value: ")
                .append(toString(obj)).append(LINE_SEPARATOR);
    }

    private static String toString(Object obj) {
        return isComplex(obj) ? "[complex value]" : isString(obj) ? "'" + obj + "'" : String.valueOf(obj);
    }
}
