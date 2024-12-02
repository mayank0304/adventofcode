import java.util.List;

public class Method {
    public static boolean isValid(List<Integer> e) {
        boolean isIncreasing = isIncreasing(e);
        boolean isDecreasing = isDecreasing(e);
        boolean isEqual = isEqual(e);
        

        if ((isDecreasing || isIncreasing) && !isEqual) {
            return isSafe(e);
        }
        return false;
    }

    public static boolean isIncreasing(List<Integer> e) {
        for (int i = 1; i < e.size(); i++) {
            if (e.get(i) < e.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDecreasing(List<Integer> e) {
        for (int i = 1; i < e.size(); i++) {
            if (e.get(i) > e.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEqual(List<Integer> e) {
        for (int i = 1; i < e.size(); i++) {
            if (e.get(i) == e.get(i - 1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSafe(List<Integer> e) {
        for (int i = 1; i < e.size(); i++) {
            if (Math.abs(e.get(i) - e.get(i - 1)) < 1 || Math.abs(e.get(i) - e.get(i - 1)) > 3) {
                return false;
            }

        }
        return true;
    }
}
