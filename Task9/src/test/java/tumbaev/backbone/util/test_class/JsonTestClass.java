package tumbaev.backbone.util.test_class;

public class JsonTestClass {
    private String stringField;
    private char charField;
    private int intField;
    private double doubleField;
    private boolean booleanField;
    private NumberEnum enumField;


    public JsonTestClass() {
    }

    public JsonTestClass(String stringField, char charField, int intField, double doubleField, boolean booleanField, NumberEnum enumField) {
        this.stringField = stringField;
        this.charField = charField;
        this.intField = intField;
        this.doubleField = doubleField;
        this.booleanField = booleanField;
        this.enumField = enumField;
    }

    public String toJson() {
        return '{' +
                "\"stringField\":\"" + stringField + "\"" +
                ",\"charField\":\"" + charField + "\"" +
                ",\"intField\":" + intField +
                ",\"doubleField\":" + doubleField +
                ",\"booleanField\":" + booleanField +
                ",\"enumField\":\"" + enumField + "\"" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JsonTestClass)) return false;

        JsonTestClass that = (JsonTestClass) o;

        if (getCharField() != that.getCharField()) return false;
        if (getIntField() != that.getIntField()) return false;
        if (Double.compare(getDoubleField(), that.getDoubleField()) != 0) return false;
        if (isBooleanField() != that.isBooleanField()) return false;
        if (!getStringField().equals(that.getStringField())) return false;
        return getEnumField() == that.getEnumField();
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public char getCharField() {
        return charField;
    }

    public void setCharField(char charField) {
        this.charField = charField;
    }

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public double getDoubleField() {
        return doubleField;
    }

    public void setDoubleField(double doubleField) {
        this.doubleField = doubleField;
    }

    public boolean isBooleanField() {
        return booleanField;
    }

    public void setBooleanField(boolean booleanField) {
        this.booleanField = booleanField;
    }

    public NumberEnum getEnumField() {
        return enumField;
    }

    public void setEnumField(NumberEnum enumField) {
        this.enumField = enumField;
    }
}
