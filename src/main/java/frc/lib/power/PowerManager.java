package frc.lib.power;

import java.util.*;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.lib.motorcontrol.BaseMotorController;
import frc.lib.power.solver.SubsystemSolvable;
import frc.lib.util.MathUtils;


public class PowerManager {

    private PowerManager() { throw new UnsupportedOperationException("This is a utility class!"); }

    private static PowerDistributionPanel pdp;

    private static final NetworkTableInstance network = NetworkTableInstance.getDefault();
    private static final NetworkTable table = network.getTable("powermonitor");

    private static final List<PowerManagedSubsystem> managedSubsystems = new ArrayList<>();

    public static void initialize(PowerDistributionPanel _pdp) {
        pdp = _pdp;
    }

    public static PowerDistributionPanel getPdp() {
        return pdp;
    }

    static void addSubsystem(PowerManagedSubsystem subsystem) {
        if (managedSubsystems.contains(subsystem)) return;

        managedSubsystems.add(subsystem);
    }

    private static List<SubsystemSolvable> calculateWeightedSubsystems() {
        // TODO: Convert to SolvableSubsystem and create weights
        // Set weights in a linear fashion relative to priorities

        managedSubsystems.sort(Comparator.comparingInt(PowerManagedSubsystem::getPriority));

        return null;
    }

    public void update() {
        // do the thing
    }
}
