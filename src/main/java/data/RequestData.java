package data;

public class RequestData {
    private final float x;
    private final int y;
    private final int r;

    public RequestData(float x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public float getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }
}