package hexlet.code.formatters;

import java.util.Map;

public interface Formatter {
    String changed(String key, Map<String, Object> map1, Map<String, Object> map2);

    String unchanged(String key, Map<String, Object> map1, Map<String, Object> map2);

    String added(String key, Map<String, Object> map1, Map<String, Object> map2);

    String removed(String key, Map<String, Object> map1, Map<String, Object> map2);
}
