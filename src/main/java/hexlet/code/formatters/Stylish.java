package hexlet.code.formatters;

import java.util.Map;

import static hexlet.code.Differ.CHANGED;
import static hexlet.code.Differ.UNCHANGED;
import static hexlet.code.Differ.REMOVED;
import static hexlet.code.Differ.LINE_SEPARATOR;

public final class Stylish implements Format {

    @Override
    public String format(Map<String, String> differenceMap,
                         Map<String, Object> firstContent,
                         Map<String, Object> secondContent) {

        StringBuilder builder = new StringBuilder("{" + LINE_SEPARATOR);
        for (Map.Entry<String, String> entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();
            switch (difference) {
                case UNCHANGED -> builder.append(doNotChange(key, firstContent.get(key)));
                case CHANGED -> builder.append(change(key, firstContent.get(key), secondContent.get(key)));
                case REMOVED -> builder.append(remove(key, firstContent.get(key)));
                default -> builder.append(add(key, secondContent.get(key)));
            }
        }
        return builder.append("}").toString();
    }

    private static StringBuilder doNotChange(String key, Object obj) {
        return new StringBuilder("    ")
                .append(key)
                .append(": ")
                .append(obj)
                .append(LINE_SEPARATOR);
    }

    private static StringBuilder change(String key, Object objFirstFile, Object objSecondFile) {
        return new StringBuilder().append(remove(key, objFirstFile)).append(add(key, objSecondFile));
    }

    private static StringBuilder remove(String key, Object obj) {
        return new StringBuilder("  - ")
                .append(key)
                .append(": ")
                .append(obj)
                .append(LINE_SEPARATOR);
    }

    private static StringBuilder add(String key, Object obj) {
        return new StringBuilder("  + ")
                .append(key)
                .append(": ")
                .append(obj)
                .append(LINE_SEPARATOR);
    }
}
