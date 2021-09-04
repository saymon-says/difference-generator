package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;

public interface Format {
    String format(Map<String, String> diffOfFiles,
                  Map<String, Object> fileName1,
                  Map<String, Object> fileName2) throws IOException;
}
