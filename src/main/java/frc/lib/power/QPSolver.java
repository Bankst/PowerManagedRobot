package frc.lib.power;

import com.cureos.numerics.Calcfc;
import com.cureos.numerics.Cobyla;
import com.cureos.numerics.CobylaExitStatus;

import frc.lib.power.solver.SubsystemSolvable;

import java.util.Arrays;

public class QPSolver {
    private double rhobeg = 0.2;
    private double rhoend = 1.0e-10;
    private int iprint = 0;
    private int maxfun = 50;

    private int numSubsystems;
    private int numConstraints;

    private double returnVal = 0.0;

    private double[] mastx;

    private SubsystemSolvable[] subsystems;

    public QPSolver(SubsystemSolvable... _subsystems){
        subsystems = _subsystems;
        numSubsystems = subsystems.length;
        numConstraints = numSubsystems*2 + 1;
        System.out.println("Sub" + numSubsystems);
        System.out.println("Constraints" + numConstraints);
    }

    public double[] solve(int maxCurrent){
        Calcfc calcfc = new Calcfc() {
            @Override
            public double Compute(int n, int m, double[] x, double[] con) {

                con[0] = maxCurrent;

                System.out.println("Con[0]" + con[0]);

                for (int i = 0; i < subsystems.length; i++){
                    con[0] -= (subsystems[i].requestedCurrent * x[i]);
                }
                
                for (int i = 0; i < subsystems.length; i++){
                    con[i+1] = (1 - x[i]);
                }

                for (int i = 0; i < subsystems.length; i++){
                    con[i + 1 + subsystems.length] = (x[i] - 0);
                }

                for (int i = 0; i < subsystems.length; i++){
                    returnVal += -(subsystems[i].weight) * Math.pow(x[i], 2.0);
                }

                System.out.println(returnVal);

                return returnVal;
            }
        };
        mastx = new double[numSubsystems];
        Arrays.fill(mastx, 0.0);
        CobylaExitStatus result = Cobyla.FindMinimum(calcfc, numSubsystems, numConstraints, mastx, rhobeg, rhoend, iprint, maxfun);
        System.out.println("0 " + mastx[0] * 140);
        System.out.println("1 " + mastx[1] * 100);
        System.out.println("2 " + mastx[2] * 140);
        System.out.println("3 " + mastx[3] * 100);
        return mastx;
    }
}
