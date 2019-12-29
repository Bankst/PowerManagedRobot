package frc.lib;

public class Motor {

    public final double kv;
    public final double r;
    public final double kt;

    public final double freeSpeed;
    public final double freeCurrent;
    public final double stallTorque;
    public final double stallCurrent;

    public Motor (double _kv, double _r, double _kt, double _freeSpeed, double _freeCurrent, double _stallTorque, double _stallCurrent) {
        kv = _kv;
        r = _r;
        kt = _kt;

        freeSpeed = _freeSpeed;
        freeCurrent = _freeCurrent;
        stallTorque = _stallTorque;
        stallCurrent = _stallCurrent;
    }

    public double predictiveCurrentLimit(double currentV, double maxI, double currentRPM) {
        double currentI = currentV / r - currentRPM / (kv * r);
        double outputV = currentV;

        if (currentI > maxI) {
            outputV = maxI * r + currentRPM / kv;
        }
        return outputV;
    }

    public double reactiveCurrentLimit(double currentV, double maxI, double currentI) {
        double omega = kv * currentV - currentI * r * kv;
        double outputV = currentV;

        if (currentI > maxI) {
            outputV = maxI * r + omega / kv;
        }
        return outputV;
    }

    public static final Motor kCIM = new Motor(451.089, 0.0914, 0.0184, 5330, 2.7, 2.41, 131);
}