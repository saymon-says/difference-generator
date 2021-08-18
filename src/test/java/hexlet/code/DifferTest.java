package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    @Test
    void generateJsonFormat() throws Exception {
        String fileJson1 = "filepath1.json";
        String fileJson2 = "filepath2.json";
        String actualContent = Differ.generate(fileJson1, fileJson2, "stylish");
        String expectedContent = Files.readString(Parser.getFixturePath("expected.json"));
        assertThat(actualContent).isEqualTo(expectedContent);
    }

    @Test
    void generateYamlFormat() throws Exception {
        String fileYaml1 = "filepath1.yaml";
        String fileYaml2 = "filepath2.yaml";
        String actualContent = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContent = Files.readString(Parser.getFixturePath("expected.json"));
        assertThat(actualContent).isEqualTo(expectedContent);
    }

    @Test
    void generateJsonNestedFormat() throws Exception {
        String fileYaml1 = "file1.json";
        String fileYaml2 = "file2.json";
        String actualContent = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContent = Files.readString(Parser.getFixturePath("expected_json.json"));
        assertThat(actualContent).isEqualTo(expectedContent);
    }

    @Test
    void generateYamlNestedFormat() throws Exception {
        String fileYaml1 = "file1.yaml";
        String fileYaml2 = "file2.yaml";
        String actualContent = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContent = Files.readString(Parser.getFixturePath("expected_json.json"));
        assertThat(actualContent).isEqualTo(expectedContent);
    }
}
