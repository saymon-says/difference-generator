package hexlet.code;

import hexlet.code.formatters.Format;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {

    public static Format getFormatter(String formatterName) {
        if (formatterName.equals("stylish")) {
            return new Stylish();
        }
        if (formatterName.equals("plain")) {
            return new Plain();
        } else {
            return new Json();
        }
    }

}
