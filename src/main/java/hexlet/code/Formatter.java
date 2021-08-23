package hexlet.code;

import hexlet.code.formatters.Format;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {

    public static Format getFormatter(String formatterName) {
        switch (formatterName) {
            case "json" -> {
                return new Json();
            }
            case "plain" -> {
                return new Plain();
            }
            default -> {
                return new Stylish();
            }
        }
    }

}
