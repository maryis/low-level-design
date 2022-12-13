package jsonparser;

public class WriteContext extends StreamContext {
    private Object value;

    public WriteContext(int idx, Object value) {
        super(idx);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
