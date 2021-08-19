package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    @Test
    void generateSimpleJsonStylishFormat() throws Exception {
        String fileJson1 = "filepath1.json";
        String fileJson2 = "filepath2.json";
        String actualContentSimpleJsonStylish = Differ.generate(fileJson1, fileJson2, "stylish");
        String expectedContentSimpleJsonStylish = Files.readString(Parser.getFixturePath("expected.txt"));
        assertThat(actualContentSimpleJsonStylish).isEqualTo(expectedContentSimpleJsonStylish);
    }

    @Test
    void generateSimpleYamlStylishFormat() throws Exception {
        String fileYaml1 = "filepath1.yaml";
        String fileYaml2 = "filepath2.yaml";
        String actualContentSimpleYamlStylish = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContentSimpleYamlStylish = Files.readString(Parser.getFixturePath("expected.txt"));
        assertThat(actualContentSimpleYamlStylish).isEqualTo(expectedContentSimpleYamlStylish);
    }

    @Test
    void generateNestedJsonStylishFormat() throws Exception {
        String fileYaml1 = "file1.json";
        String fileYaml2 = "file2.json";
        String actualContentNestedJsonStylish = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContentNestedJsonStylish = Files.readString(Parser.getFixturePath("expected_json.txt"));
        assertThat(actualContentNestedJsonStylish).isEqualTo(expectedContentNestedJsonStylish);
    }

    @Test
    void generateNestedYamlStylishFormat() throws Exception {
        String fileYaml1 = "file1.yaml";
        String fileYaml2 = "file2.yaml";
        String actualContentNestedYamlStylish = Differ.generate(fileYaml1, fileYaml2, "stylish");
        String expectedContentNestedYamlStylish = Files.readString(Parser.getFixturePath("expected_json.txt"));
        assertThat(actualContentNestedYamlStylish).isEqualTo(expectedContentNestedYamlStylish);
    }

//    @Test
//    void generateNestedYamlPlainFormat() throws Exception {
//        String fileYaml1 = "file1.yaml";
//        String fileYaml2 = "file2.yaml";
//        String actualContentNestedYamlPlain = Differ.generate(fileYaml1, fileYaml2, "plain");
//        String expectedContentNestedYamlPlain = Files.readString(Parser.getFixturePath("expected_plain.txt"));
//        assertThat(actualContentNestedYamlPlain).isEqualTo(expectedContentNestedYamlPlain);
//    }

    @Test
    void generateSimpleJsonJsonFormat() throws Exception {
        String fileJson1 = "filepath1.json";
        String fileJson2 = "filepath2.json";
        String actualContentSimpleJsonJson = Differ.generate(fileJson1, fileJson2, "json");
        String expectedContentSimpleJsonJson = Files.readString(Parser.getFixturePath("expected_string_to_json.json"));
        assertThat(actualContentSimpleJsonJson).isEqualTo(expectedContentSimpleJsonJson);
    }

    @Test
    void generateSimpleYamlJsonFormat() throws Exception {
        String fileJson1 = "filepath1.yaml";
        String fileJson2 = "filepath2.yaml";
        String actualContentSimpleJsonJson = Differ.generate(fileJson1, fileJson2, "json");
        String expectedContentSimpleJsonJson = Files.readString(Parser.getFixturePath("expected_string_to_json.json"));
        assertThat(actualContentSimpleJsonJson).isEqualTo(expectedContentSimpleJsonJson);
    }
}
