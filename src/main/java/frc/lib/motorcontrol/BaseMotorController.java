package frc.lib.motorcontrol;

import frc.lib.*;

public abstract class BaseMotorController {
    
    private final MotorModel motorModel;

    public BaseMotorController(MotorModel _motorModel) {
        motorModel = _motorModel;
    }


    public abstract void set(double power);
    public abstract void setAppliedVoltage();
    public abstract double get();
    public double getAppliedVoltage() {
        // return RobotController.?
        return -1;
    }
}