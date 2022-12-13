package jsonparser;

public abstract class StreamContext {
    protected int idx;

    public StreamContext(int idx) {
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
}
