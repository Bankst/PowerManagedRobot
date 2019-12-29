package frc.lib.motorcontrol;

import frc.lib.*;

public abstract class BaseMotorController {
    
    protected final Motor motor;

    protected final String subsystem;

    protected double currentLimit = Double.NaN;

    public BaseMotorController(Motor _motor, String _subsystem) {
        motor = _motor;
        subsystem = _subsystem;
    }

    public abstract void set(double power);

    public abstract void setOutputVoltage(double voltage);

    public abstract double get();

    public abstract double getOutputVoltage();

    public abstract double getInputVoltage();

    public abstract double getOutputCurrent();

    public Motor getMotor(){
        return motor;
    }

    public void setCurrentLimit(double limit) {
        currentLimit = limit;
    }

    public double getCurrentLimit(){
        return currentLimit;
    }

    public boolean isCurrentLimiting(){
        return !Double.isNaN(currentLimit);
    }

    public String getSubsystem() {
        return subsystem;
    }

}