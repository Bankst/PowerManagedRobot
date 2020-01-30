package frc.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MotorTest {
  private Motor motor = Motor.kCIM;
  private double power = 0.0;
  private double RPM = 0.0;
  private ArrayList<Double> currents = new ArrayList<Double>();

  @Test
  void currentLimitingTest() {

    for(int i = 0; i < 20; i++){
        currents.add(motor.current(RPM, motor.predictiveCurrentLimit(power, 20, RPM)));
        
    }
    System.out.println(currents);
  }
}