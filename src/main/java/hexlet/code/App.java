package hexlet.code;

import picocli.CommandLine;

import java.io.File;


@CommandLine.Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public final class App implements Runnable {

    @CommandLine.Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]")
    private File archive;

    @CommandLine.Parameters(paramLabel = "filepath1", description = "path to first file")
    private String files1;

    @CommandLine.Parameters(paramLabel = "filepath2", description = "path to second file")
    private String files2;


    public void run() {
        try {
            System.out.println(Differ.generate(files1, files2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        CommandLine.run(new App(), System.err, args);
    }
}
