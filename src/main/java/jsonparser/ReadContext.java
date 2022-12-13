package jsonparser;

public class ReadContext extends StreamContext {
    private String json;

    public ReadContext(int idx, String json) {
        super(idx);
        this.json = json;
    }

    public String getJson() {
        return json;
    }

    public boolean ended() {
        return idx >= json.length()
                || json.charAt(idx) == SpecialCharType.END_OBJECT.getValue();
    }

    public char readNext() {
        return json.charAt(idx);
    }

    public void goNext() {
        this.idx++;
    }
}
