package pizzaprice;

public enum Size {
    SMALL(1),
    LARGE(15),
    MEDIUM(50);

    private int percent;
    private Size(int percent) {
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }
}
