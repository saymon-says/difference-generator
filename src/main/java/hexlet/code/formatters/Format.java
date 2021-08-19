package hexlet.code.formatters;

import java.io.IOException;

public interface Format {
    String getResult(String fileName1, String fileName2) throws IOException;
}
