package tumbaev.backbone.util;

import tumbaev.backbone.exception.JsonConversionException;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Converts json to string and vice versa.
 * <p>
 * Doesn't support arrays and nested objects.
 */
public final class JsonConverter {
    private JsonConverter() {
        throw new UnsupportedOperationException("This is an utility class");
    }

    /**
     * Converts the object to a json string.
     * <p>
     * Values of this object must be primitives, strings or enums. Arrays and nested objects are not supported.
     *
     * @param obj object to convert
     * @param <T> type of object
     * @return json representation of provided object
     */
    public static <T> String toJson(T obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        Map<String, Object> jsonMap = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                jsonMap.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                throw new JsonConversionException(String.format("Cannot access property %s", field.getName()));
            }
        }

        return mapToJson(jsonMap);
    }

    /**
     * Converts a json string to an object of specified type.
     * <p>
     * Doesn't support arrays and nested objects.
     *
     * @param json  string to convert
     * @param clazz object of which class should be created
     * @param <T>   type of object
     * @return object of type T
     * @throws JsonConversionException json or clazz was null,
     *                                 json cannot be converted,
     *                                 failed to create an instance of clazz,
     *                                 clazz does not have properties declared in json,
     *                                 fields of clazz are not accessible
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        if (clazz == null) {
            throw new JsonConversionException("Cannot convert, because class was null");
        }
        Map<String, String> objAsMap = jsonToMap(json);

        T obj;
        try {
            obj = clazz.getConstructor().newInstance();
        } catch (Throwable e) {
            throw new JsonConversionException(
                    String.format("Cannot create object of type '%s'", clazz.getName()), e);
        }

        for (Field field : clazz.getDeclaredFields()) {
            String value = objAsMap.get(field.getName());
            if (value == null) {
                throw new JsonConversionException(
                        String.format("Class '%s' does not have property '%s'", clazz.getName(), field.getName()));
            }
            setValue(obj, field, value);
        }
        return obj;
    }

    /**
     * Tries to set the value to the field of the object.
     *
     * @param obj   object
     * @param field field of obj
     * @param value value that should be set
     * @param <T>   type of object
     * @throws JsonConversionException failed to set the value
     */
    private static <T> void setValue(T obj, Field field, String value) {
        field.setAccessible(true);
        Class<?> fieldType = field.getType();
        if (fieldType == char.class || fieldType.equals(Character.class)) {
            if (value.length() != 1) {
                throw new JsonConversionException(
                        String.format("Cannot convert '%s' to char", value));
            }
            try {
                field.set(obj, value.charAt(0));
                return;
            } catch (IllegalAccessException e) {
                throw new JsonConversionException(
                        String.format("Cannot set property '%s' of type '%s", field.getName(), fieldType.getName()), e);
            }
        }

        PropertyEditor editor = PropertyEditorManager.findEditor(fieldType);
        if (editor == null) {
            throw new JsonConversionException(
                    String.format("Cannot find editor for field '%s' of type '%s", field.getName(), fieldType.getName()));
        }
        editor.setAsText(value);
        try {
            field.set(obj, editor.getValue());
        } catch (IllegalAccessException e) {
            throw new JsonConversionException(
                    String.format("Cannot set property '%s' of type '%s'", field.getName(), fieldType.getName()));
        }

    }

    /**
     * Converts a json string to a map.
     * <p>
     * Doesn't support arrays and nested objects.
     *
     * @param json string to convert
     * @return a map with keys and values of the given json string
     * @throws JsonConversionException json was null or it cannot be converted
     */
    private static Map<String, String> jsonToMap(String json) {
        if (json == null) {
            throw new JsonConversionException("Cannot convert, because json was null");
        }
        String[] keyValuePairs = json.replace("{", "").replace("}", "").split(",");

        if (keyValuePairs.length == 0) {
            throw new JsonConversionException(
                    String.format("Cannot convert '%s', because it's either empty or not a json", json));
        }

        Map<String, String> jsonMap = new HashMap<>(keyValuePairs.length);
        for (String pair : keyValuePairs) {
            String[] entry = pair.split(":");
            try {
                String key = entry[0].trim().replace("\"", "");
                String value = entry[1].trim().replace("\"", "");
                jsonMap.put(key, value);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new JsonConversionException(String.format("Cannot convert '%s'", json));
            }
        }
        return jsonMap;
    }

    /**
     * Converts the map to a json string.
     * <p>
     * Values of this map must be primitives, strings or enums. Arrays and nested objects are not supported.
     *
     * @param map map to convert
     * @return json representation of provided map
     */
    private static String mapToJson(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\":");
            Object value = entry.getValue();
            if (value instanceof String || value instanceof Enum || value instanceof Character) {
                sb.append("\"").append(value).append("\"");
            } else {
                sb.append(value);
            }
            sb.append(",");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("}");

        return sb.toString();
    }
}
