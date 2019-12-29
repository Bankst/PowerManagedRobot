package frc.lib.motorcontrol;

import edu.wpi.first.wpilibj.RobotController;
import frc.lib.Motor;

public class SimpleMotorController extends BaseMotorController {

    private double setpoint;

    public SimpleMotorController(Motor _motor) {
        super(_motor);
    }

    @Override
    public void set(double power) {
        setpoint = clamp(power);
    }

    @Override
    public void setOutputVoltage(double voltage) {
        set(voltage / getInputVoltage());
    }

    @Override
    public double get() {
        return setpoint;
    }

    @Override
    public double getOutputVoltage() {
        return setpoint * getInputVoltage();
    }

    @Override
    public double getInputVoltage() {
        return RobotController.getBatteryVoltage();
    }

    private double clamp(double value) {
        if (value > 1.0) value = 1.0;
        if (value < -1.0) value = -1.0;
        return value;
    }
}
