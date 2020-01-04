package frc.lib.motorcontrol;

import frc.lib.*;
import frc.lib.power.PowerManagedSubsystem;

public abstract class BaseMotorController {
    
    final Motor motor;
    private final String controllerName;
    private final int maxCurrentLimit;

    private double currentLimit = Double.NaN;

    public BaseMotorController(Motor _motor, String _controllerName, int _maxCurrentLimit) {
        motor = _motor;
        controllerName = _controllerName;
        maxCurrentLimit = _maxCurrentLimit;
    }

    public abstract void set(double power);

    public abstract void setOutputVoltage(double voltage);

    public abstract double get();

    public abstract double getOutputVoltage();

    public abstract double getInputVoltage();

    public abstract double getOutputCurrent();

    public Motor getMotor() {
        return motor;
    }

    public void setCurrentLimit(double limit) {
        currentLimit = limit;
    }

    public double getCurrentLimit() {
        return currentLimit;
    }

    public boolean isCurrentLimiting() {
        return !Double.isNaN(currentLimit);
    }

    public String getControllerName() {
        return controllerName;
    }

    public double getMaxCurrentLimit() { return maxCurrentLimit; }
}