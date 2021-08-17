package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    private static String file1 = "filepath1.json";
    private static String file2 = "filepath2.json";


    @Test
    void generate() throws Exception {
        String actualContent = Differ.generate(file1, file2);
        String expectedContent = Files.readString(Differ.getFixturePath("expected.json"));
        assertThat(actualContent).isEqualTo(expectedContent);
    }
}
