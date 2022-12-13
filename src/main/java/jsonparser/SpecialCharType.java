package jsonparser;

public enum  SpecialCharType {
    END_OBJECT('}'),
    OBJECT('{'),
    COLON(':'),
    QUOT('"'),
    ARRAY('['),
    END_ARRAY(']'),
    COMMA(',');

    private final char value;

    SpecialCharType(char ch) {
        value = ch;
    }

    public char getValue() {
        return value;
    }
}
