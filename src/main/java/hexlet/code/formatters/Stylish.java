package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Parser;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static hexlet.code.Differ.CHANGED;
import static hexlet.code.Differ.UNCHANGED;
import static hexlet.code.Differ.REMOVED;
import static hexlet.code.Differ.LINE_SEPARATOR;

public final class Stylish implements Format {

    @Override
    public String format(String fileName1, String fileName2) throws IOException {
        Map<String, Object> mapOfFile1 = Parser.getMapFromFile(fileName1);
        Map<String, Object> mapOfFile2 = Parser.getMapFromFile(fileName2);
        Set<String> keySet = Parser.getKeySet(mapOfFile1, mapOfFile2);

        Map<String, String> diff = Differ.getDiffFile(keySet, mapOfFile1, mapOfFile2);
        StringBuilder builder = new StringBuilder("{" + LINE_SEPARATOR);
        for (Map.Entry<String, String> entry : diff.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();
            switch (difference) {
                case UNCHANGED -> builder.append(doNotChange(key, mapOfFile1));
                case CHANGED -> builder.append(change(key, mapOfFile1, mapOfFile2));
                case REMOVED -> builder.append(remove(key, mapOfFile1));
                default -> builder.append(add(key, mapOfFile2));
            }
        }
        return builder.append("}").toString();
    }

    private static StringBuilder doNotChange(String key, Map<String, Object> map1) {
        return new StringBuilder("    ")
                .append(key)
                .append(": ")
                .append(map1.get(key))
                .append(LINE_SEPARATOR);
    }

    private static StringBuilder change(String key, Map<String, Object> map1, Map<String, Object> map2) {
        return new StringBuilder().append(remove(key, map1)).append(add(key, map2));
    }

    private static StringBuilder remove(String key, Map<String, Object> map1) {
        return new StringBuilder("  - ")
                .append(key)
                .append(": ")
                .append(map1.get(key))
                .append(LINE_SEPARATOR);
    }

    private static StringBuilder add(String key, Map<String, Object> map2) {
        return new StringBuilder("  + ")
                .append(key)
                .append(": ")
                .append(map2.get(key))
                .append(LINE_SEPARATOR);
    }
}
