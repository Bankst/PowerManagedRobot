package frc.lib.power;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.networktables.NetworkTable;
import frc.lib.motorcontrol.BaseMotorController;


public class PowerManager {

    private PowerManager() { throw new UnsupportedOperationException("This is a utility class!"); }

    private static PowerDistributionPanel pdp;

    private static final NetworkTableInstance network = NetworkTableInstance.getDefault();
    private static final NetworkTable table = network.getTable("powermonitor");

    private static final HashMap<String, BaseMotorController> controllers = new HashMap<>();

    public static void initialize(PowerDistributionPanel _pdp) {
        pdp = _pdp;
    }

    public static PowerDistributionPanel getPdp() {
        return pdp;
    }

    // TODO refactor to subsystems
//    public static void addController(BaseMotorController controller, String name) {
//        controllers.put(name, controller);
//        table.getSubTable(controller.getParentSubsystem().getName()).getSubTable(name).getEntry("Current").setDouble(controller.getOutputCurrent());
//        table.getSubTable(controller.getParentSubsystem().getName()).getSubTable(name).getEntry("Limit").setDouble(controller.getCurrentLimit());
//    }
//
//    public static void update() {
//        for(Map.Entry<String, BaseMotorController> mapElement : controllers.entrySet()) {
//            String name = mapElement.getKey();
//            BaseMotorController controller = mapElement.getValue();
//
//            table.getSubTable(controller.getParentSubsystem().getName()).getSubTable(name).getEntry("Current").setDouble(controller.getOutputCurrent());
//            controller.setCurrentLimit(table.getSubTable(controller.getParentSubsystem().getName()).getSubTable(name).getEntry("Limit").getDouble(Double.NaN));
//        }
//    }
}
