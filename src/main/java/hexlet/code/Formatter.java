package hexlet.code;

import hexlet.code.formatters.Format;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {

    public static Format getFormatter(String formatterName) {
        if (formatterName.equals("stylish")) {
            return new Stylish();
        } else {
            return new Plain();
        }
    }

}
