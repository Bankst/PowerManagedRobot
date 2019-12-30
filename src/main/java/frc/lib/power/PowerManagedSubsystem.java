package frc.lib.power;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.motorcontrol.BaseMotorController;

import java.util.ArrayList;
import java.util.List;

public class PowerManagedSubsystem extends SubsystemBase {

    protected final List<BaseMotorController> managedControllers = new ArrayList<>();

    private int priority;

    public PowerManagedSubsystem(String _subsystemName) {
        this(_subsystemName, -1);
    }

    public PowerManagedSubsystem(String _subsystemName, int _priority) {
        setName(_subsystemName);
        priority = _priority;
    }

    protected void manageController(BaseMotorController controller) {
        if (!managedControllers.contains(controller)) {
            managedControllers.add(controller);
        }
    }

    public double getTotalCurrentUsage() {
        double currentDraw = 0;

        for (var bmc : managedControllers) {
            currentDraw += bmc.getOutputCurrent();
        }

        return currentDraw;
    }

    public double getTotalPowerUsage() {
        return RobotController.getBatteryVoltage() * getTotalCurrentUsage();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
