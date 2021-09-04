package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> getMapFromFile(String fileContents, String fileName) throws IOException {
        if (fileName.endsWith(".json")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(fileContents, new TypeReference<>() {
            });
        } else {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(fileContents, new TypeReference<>() {
            });
        }
    }
}
