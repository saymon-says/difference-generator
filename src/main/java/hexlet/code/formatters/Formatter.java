package hexlet.code.formatters;

import java.util.Map;
import java.util.stream.Collectors;

public class Formatter {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static <K, V> String stylish(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(LINE_SEPARATOR, "{" + LINE_SEPARATOR, LINE_SEPARATOR + "}"));
    }

    public static <K, V> String plain(Map<K, V> map) {
        return null;
    }

}
