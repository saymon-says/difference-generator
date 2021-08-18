package hexlet.code.formatters;

import java.util.Map;
import java.util.stream.Collectors;

public class Formatter {

    public static <K, V> String stylish(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
//                .collect(Collectors.joining("\r\n", "{\r\n", "\r\n}"));
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    public static <K, V> String plain(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\r\n", "{\r\n", "\r\n}"));
    }

}
