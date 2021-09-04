package hexlet.code.formatters;

import java.util.Map;

import static hexlet.code.Differ.CHANGED;
import static hexlet.code.Differ.UNCHANGED;
import static hexlet.code.Differ.REMOVED;
import static hexlet.code.Differ.LINE_SEPARATOR;

public final class Stylish implements Format {

    @Override
    public String format(Map<String, String> diffOfFiles,
                         Map<String, Object> fileName1,
                         Map<String, Object> fileName2) {

        StringBuilder builder = new StringBuilder("{" + LINE_SEPARATOR);
        for (Map.Entry<String, String> entry : diffOfFiles.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();
            switch (difference) {
                case UNCHANGED -> builder.append(doNotChange(key, fileName1));
                case CHANGED -> builder.append(change(key, fileName1, fileName2));
                case REMOVED -> builder.append(remove(key, fileName1));
                default -> builder.append(add(key, fileName2));
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
