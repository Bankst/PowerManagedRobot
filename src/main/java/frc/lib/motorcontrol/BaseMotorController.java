package frc.lib.motorcontrol;

import frc.lib.*;

public abstract class BaseMotorController {
    
    private final MotorModel motorModel;

    public BaseMotorController(MotorModel _motorModel) {
        motorModel = _motorModel;
    }

    public abstract void set(double power);

    public abstract void setOutputVoltage(double voltage);

    public abstract double get();

    public abstract double getOutputVoltage();

    public abstract double getInputVoltage();
}