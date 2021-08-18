package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Path getFixturePath(String fileName) {
        return Paths.get("src/test/java/resources/", fileName).toAbsolutePath().normalize();
    }

    public static File getFile(String fileName) {
        return new File(String.valueOf(getFixturePath(fileName)));
    }

    public static Map<String, Object> getMapFromFile(String filePath) throws IOException {
        if (filePath.endsWith(".yaml")) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(getFile(filePath), new TypeReference<>() {
            });
        } else {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(getFile(filePath), new TypeReference<>() {
            });
        }
    }

}
