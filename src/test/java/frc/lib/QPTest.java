package frc.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import frc.lib.power.QPSolver;
import frc.lib.power.solver.SubsystemSolvable;

class QPTest {
  private SubsystemSolvable subsystem1 = new SubsystemSolvable();
  private SubsystemSolvable subsystem2 = new SubsystemSolvable();
  private SubsystemSolvable subsystem3 = new SubsystemSolvable();
  private SubsystemSolvable subsystem4 = new SubsystemSolvable();

  private QPSolver solver;

  @Test
  void test() {
    subsystem1.requestedCurrent = 140;
    subsystem2.requestedCurrent = 100;
    subsystem3.requestedCurrent = 140;
    subsystem4.requestedCurrent = 100;

    subsystem1.weight = 0.3;
    subsystem2.weight = 0.5;
    subsystem3.weight = 0.4;
    subsystem4.weight = 0.6;


    solver = new QPSolver(subsystem1, subsystem2, subsystem3, subsystem4);

    solver.solve(300);
  }
}