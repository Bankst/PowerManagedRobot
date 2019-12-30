package frc.lib.motorcontrol;

import frc.lib.*;
import frc.lib.power.PowerManagedSubsystem;

public abstract class BaseMotorController {
    
    protected final Motor motor;
    protected final String controllerName;

    protected int priority;
    protected double currentLimit = Double.NaN;

    public BaseMotorController(Motor _motor, String _controllerName) {
        this(_motor, _controllerName, -1);
    }

    public BaseMotorController(Motor _motor, String _controllerName, int _priority) {
        motor = _motor;
        controllerName = _controllerName;
        priority = _priority;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}