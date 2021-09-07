package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;

public interface Format {
    String format(Map<String, String> differenceMap,
                  Map<String, Object> firstContent,
                  Map<String, Object> secondContent) throws IOException;
}
