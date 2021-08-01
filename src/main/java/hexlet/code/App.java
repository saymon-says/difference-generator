package hexlet.code;

import picocli.CommandLine;

import java.io.File;


@CommandLine.Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Runnable {

    @CommandLine.Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]")
    File archive;

    @CommandLine.Parameters(paramLabel = "filepath1", description = "path to first file")
    File files1;

    @CommandLine.Parameters(paramLabel = "filepath2", description = "path to second file")
    File files2;


    public void run() {
        System.out.println("Hello, ");
    }

    public static void main(String... args) {
        CommandLine.run(new App(), System.err, args);
    }
}