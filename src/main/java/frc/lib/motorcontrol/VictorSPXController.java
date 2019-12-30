package frc.lib.motorcontrol;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.RobotController;
import frc.lib.Motor;
import frc.lib.power.PowerManager;

public class VictorSPXController extends BaseMotorController {

    public final WPI_VictorSPX controller;

    private int PDPport;

    public VictorSPXController(int _port, Motor _motor, int _PDPport, String _subsystem) {
        super(_motor, _subsystem);
        controller = new WPI_VictorSPX(_port);
        controller.enableVoltageCompensation(true);
        PDPport = _PDPport;
    }

    public VictorSPXController(int _port, Motor _motor, String _subsystem) {
        super(_motor, _subsystem);
        controller = new WPI_VictorSPX(_port);
        controller.enableVoltageCompensation(true);

        PDPport = -1;
    }
    
    @Override
    public void set(double power) {
        if (PDPport >= 0){
            controller.set(motor.reactiveCurrentLimit(clamp(power), getCurrentLimit(), PowerManager.getPdp().getCurrent(PDPport)));
        } else {
            // Is this the right thing to do?
            setCurrentLimit(Double.NaN);
            throw new UnsupportedOperationException("If no PDP port is provided an encoder value should be supplied!");
        }
    }

    public void set(double power, double RPMvalue) {
        controller.set(motor.predictiveCurrentLimit(clamp(power), getCurrentLimit(), RPMvalue));
    }

    @Override
    public double getOutputCurrent() {
        if (PDPport >= 0){
            return PowerManager.getPdp().getCurrent(PDPport);
        } else {
            // Is this the right thing to do?
            setCurrentLimit(Double.NaN);
            throw new UnsupportedOperationException("If no PDP port is provided an encoder value should be supplied!");
        }
    }

    @Override
    public void setOutputVoltage(double voltage) {
        set(voltage / getInputVoltage());
    }

    @Override
    public double get() {
        return controller.get();
    }

    @Override
    public double getOutputVoltage() {
        return controller.get() * getInputVoltage();
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
