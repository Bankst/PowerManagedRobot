package frc.lib.util;

public class MathUtils {
	private MathUtils() { throw new UnsupportedOperationException("This is a utility class!"); }
	
	public static double clamp(double input, double minimum, double maximum) {
        if (input > maximum) input = maximum;
        if (input < minimum) input = minimum;
        return input;
    }
}