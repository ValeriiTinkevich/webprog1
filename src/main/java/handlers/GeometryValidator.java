package handlers;


public class GeometryValidator {
    public static boolean isInsideArea(float x, int y, int r) {

        if (((float) -r / 2 <= x) && (x <= 0) && (0 <= y) && (y <= 10)) {
            return true;
        }

        if ((-r <= x) && (x <= 0) && ((-2 * x - 11) <= y) && (y <= 0)) {
            return true;
        }


        if ((x > 0) && (y < 0) && ((Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(r, 2))) {
            return true;
        }
        return false;
    }
}
