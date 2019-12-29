package frc.lib;

public class MotorModel {

    private final Motor motor;

    public MotorModel(final Motor _motor) {
        motor = _motor;
    }

    public double currentLimit(double currentV, double maxI, double currentI) {
        double omega = motor.kv * currentV - currentI * motor.r * motor.kv;
        double outputV = currentV;
        if (currentI > maxI) {
            outputV = maxI * motor.r + omega / motor.kv;
        }
        return outputV;
    }

}