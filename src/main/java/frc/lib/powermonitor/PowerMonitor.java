package frc.lib.powermonitor;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.networktables.NetworkTable;
import frc.lib.motorcontrol.BaseMotorController;


public class PowerMonitor{
    private static PowerMonitor single_instance = null;

    public final PowerDistributionPanel panel;

    private final NetworkTableInstance network = NetworkTableInstance.getDefault();
    private final NetworkTable table = network.getTable("powermonitor");

    private HashMap<String, BaseMotorController> controllers = new HashMap<>();

    public int PDPchannel;

    public PowerMonitor(int _PDPchannel) {
        PDPchannel = _PDPchannel;
        panel = new PowerDistributionPanel(PDPchannel);
    }

    public void addController(BaseMotorController controller, String name) {
        controllers.put(name, controller);
        table.getSubTable(controller.getSubsystem()).getSubTable(name).getEntry("Current").setDouble(controller.getOutputCurrent());
        table.getSubTable(controller.getSubsystem()).getSubTable(name).getEntry("Limit").setDouble(controller.getCurrentLimit());
    }

    public void update(){
        for(Map.Entry<String, BaseMotorController> mapElement : controllers.entrySet()) {
            String name = mapElement.getKey();
            BaseMotorController controller = mapElement.getValue();

            table.getSubTable(controller.getSubsystem()).getSubTable(name).getEntry("Current").setDouble(controller.getOutputCurrent());
            controller.setCurrentLimit(table.getSubTable(controller.getSubsystem()).getSubTable(name).getEntry("Limit").getDouble(Double.NaN));
        }
    }

    public static PowerMonitor getInstance(){ 
        if (single_instance == null) 
            single_instance = new PowerMonitor(0); 
  
        return single_instance; 
    } 
}
