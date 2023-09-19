package tumbaev.backbone.util;

import org.junit.jupiter.api.Test;
import tumbaev.backbone.util.test_class.JsonTestClass;
import tumbaev.backbone.util.test_class.NumberEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonConverterTest {

    @Test
    void testShouldConvertObjectToJsonCorrectly() {
        JsonTestClass testClass = new JsonTestClass("string", 'c', 10, 10.1, true, NumberEnum.ONE);
        assertTrue(correctJson(JsonConverter.toJson(testClass),
                testClass.getStringField(),
                testClass.getCharField(),
                testClass.getIntField(),
                testClass.getDoubleField(),
                testClass.isBooleanField(),
                testClass.getEnumField()));
    }

    @Test
    void testShouldConvertJsonToObjectCorrectly() {
        JsonTestClass testClass = new JsonTestClass("string", 'c', 10, 10.1, true, NumberEnum.ONE);
        assertEquals(testClass, JsonConverter.toObject(testClass.toJson(), JsonTestClass.class));
    }

    /**
     * Checks that json contains specified fields and values, and that it contains opening and closing braces.
     *
     * @return true if correct, false otherwise
     * @see JsonTestClass
     */
    private boolean correctJson(String json, String stringFiled, char charField, int intField, double doubleField, boolean booleanField, NumberEnum enumField) {
        return json.charAt(0) == '{' && json.charAt(json.length() - 1) == '}' &&
                json.contains("\"stringField\":\"" + stringFiled + "\"") &&
                json.contains("\"charField\":\"" + charField + "\"") &&
                json.contains("\"intField\":" + intField) &&
                json.contains("\"doubleField\":" + doubleField) &&
                json.contains("\"booleanField\":" + booleanField) &&
                json.contains("\"enumField\":\"" + enumField + "\"");
    }
}
