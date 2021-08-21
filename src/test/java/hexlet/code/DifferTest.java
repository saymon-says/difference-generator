package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    private static final String PATH = "src/test/java/resources/fixtures/";

    @Test
    void generateSimpleJsonStylishFormat() throws Exception {
        String fileJson1 = PATH + "file1.json";
        String fileJson2 = PATH + "file2.json";
        String actualContentSimpleJsonStylish = Differ.generate(fileJson1, fileJson2, "stylish");
        String expectedContentSimpleJsonStylish =
                Files.readString(Parser.getFixturePath(PATH + "expected_simple_stylish.txt"));
        assertThat(actualContentSimpleJsonStylish).isEqualTo(expectedContentSimpleJsonStylish);
    }

    @Test
    void generateSimpleYamlStylishFormat() throws Exception {
        String fileYaml1 = PATH + "file1.yaml";
        String fileYaml2 = PATH + "file2.yaml";
        String actualContentSimpleYamlStylish = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContentSimpleYamlStylish =
                Files.readString(Parser.getFixturePath(PATH + "expected_simple_stylish.txt"));
        assertThat(actualContentSimpleYamlStylish).isEqualTo(expectedContentSimpleYamlStylish);
    }

    @Test
    void generateNestedJsonStylishFormat() throws Exception {
        String fileYaml1 = PATH + "nested_json_1.json";
        String fileYaml2 = PATH + "nested_json_2.json";
        String actualContentNestedJsonStylish = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContentNestedJsonStylish = Files.
                readString(Parser.getFixturePath(PATH + "expected_nested_stylish.txt"));
        assertThat(actualContentNestedJsonStylish).isEqualTo(expectedContentNestedJsonStylish);
    }

    @Test
    void generateNestedYamlStylishFormat() throws Exception {
        String fileYaml1 = PATH + "nested_yaml_1.yaml";
        String fileYaml2 = PATH + "nested_yaml_2.yaml";
        String actualContentNestedYamlStylish = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContentNestedYamlStylish =
                Files.readString(Parser.getFixturePath(PATH + "expected_nested_stylish.txt"));
        assertThat(actualContentNestedYamlStylish).isEqualTo(expectedContentNestedYamlStylish);
    }

    @Test
    void generateNestedYamlPlainFormat() throws Exception {
        String fileYaml1 = PATH + "nested_yaml_1.yaml";
        String fileYaml2 = PATH + "nested_yaml_2.yaml";
        String actualContentNestedYamlPlain = Differ.generate(fileYaml1, fileYaml2, "plain");
        String expectedContentNestedYamlPlain =
                Files.readString(Parser.getFixturePath(PATH + "expected_simple_plain.txt")).replaceAll("\r", "");
        assertThat(actualContentNestedYamlPlain).isEqualTo(expectedContentNestedYamlPlain);
    }

    @Test
    void generateSimpleJsonJsonFormat() throws Exception {
        String fileJson1 = PATH + "file1.json";
        String fileJson2 = PATH + "file2.json";
        String actualContentSimpleJsonJson = Differ.generate(fileJson1, fileJson2, "json");
        String expectedContentSimpleJsonJson =
                Files.readString(Parser.getFixturePath(PATH + "expected_simple_json.json")).replaceAll("\r", "");
        assertThat(actualContentSimpleJsonJson).isEqualTo(expectedContentSimpleJsonJson);
    }

    @Test
    void generateNestedYamlJsonFormat() throws Exception {
        String fileJson1 = PATH + "nested_yaml_1.yaml";
        String fileJson2 = PATH + "nested_yaml_2.yaml";
        String actualContentSimpleJsonJson = Differ.generate(fileJson1, fileJson2, "json");
        String expectedContentSimpleJsonJson =
                Files.readString(Parser.getFixturePath(PATH + "expected_nested_yaml.json")).replaceAll("\r", "");
        assertThat(actualContentSimpleJsonJson).isEqualTo(expectedContentSimpleJsonJson);
    }

    @Test
    void generateSimpleJsonDefaultFormat() throws Exception {
        String fileJson1 = PATH + "file1.json";
        String fileJson2 = PATH + "file2.json";
        String actualContentSimpleJsonStylish = Differ.generate(fileJson1, fileJson2);
        String expectedContentSimpleJsonStylish =
                Files.readString(Parser.getFixturePath(PATH + "expected_simple_stylish.txt"));
        assertThat(actualContentSimpleJsonStylish).isEqualTo(expectedContentSimpleJsonStylish);
    }
}
