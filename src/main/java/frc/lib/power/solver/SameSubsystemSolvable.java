package frc.lib.power.solver;

public class SameSubsystemSolvable extends SubsystemSolvable {

    public SameSubsystemSolvable(double weight, SubsystemSolvable... solvables) {
        for(var solvable : solvables) {
            solvable.weight = weight;
            requestedCurrent += solvable.requestedCurrent;
        }
    }
}
