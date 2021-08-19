package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Parser;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static hexlet.code.Differ.CHANGED;
import static hexlet.code.Differ.UNCHANGED;
import static hexlet.code.Differ.REMOVED;
import static hexlet.code.Differ.ADDED;

public final class Stylish implements Format {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Override
    public String getResult(String fileName1, String fileName2) throws IOException {
        Map<String, Object> mapOfFile1 = Parser.getMapFromFile(fileName1);
        Map<String, Object> mapOfFile2 = Parser.getMapFromFile(fileName2);
        Set<String> keySet = Parser.getKeySet(mapOfFile1, mapOfFile2);

        Map<String, String> diff = Differ.getDiffFile(keySet, mapOfFile1, mapOfFile2);
        StringBuilder builder = new StringBuilder("{" + LINE_SEPARATOR);
        for (Map.Entry<String, String> entry : diff.entrySet()) {
            String key = entry.getKey();
            String difference = entry.getValue();
            if (difference.equals(UNCHANGED)) {
                builder.append("    ")
                        .append(key)
                        .append(": ")
                        .append(mapOfFile1.get(key))
                        .append(LINE_SEPARATOR);
            }
            if (difference.equals(CHANGED)) {
                builder.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(mapOfFile1.get(key))
                        .append(LINE_SEPARATOR)
                        .append("  + ")
                        .append(key)
                        .append(": ")
                        .append(mapOfFile2.get(key))
                        .append(LINE_SEPARATOR);
            }
            if (difference.equals(REMOVED)) {
                builder.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(mapOfFile1.get(key))
                        .append(LINE_SEPARATOR);
            }
            if (difference.equals(ADDED)) {
                builder.append("  + ")
                        .append(key)
                        .append(": ")
                        .append(mapOfFile2.get(key))
                        .append(LINE_SEPARATOR);
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
