package frc.lib.power;

import com.cureos.numerics.Calcfc;
import com.cureos.numerics.Cobyla;
import com.cureos.numerics.CobylaExitStatus;

public class QPSolver {

    public static void main(String[] args) {
        double rhobeg = 0.1;
        double rhoend = 1.0e-10;
        int iprint = 1;
        int maxfun = 100;

        Calcfc calcfc = new Calcfc() {
            @Override
            public double Compute(int n, int m, double[] x, double[] con) {
                con[0] = 120 - ((140 * x[0]) + (100 * x[1]));
                con[1] = 1 - x[0];
                con[2] = 1 - x[1];
                return -0.3 * Math.pow(x[0], 2.0) + -0.6 * Math.pow(x[1], 2.0);
            }
        };
        double[] x = {0.0, 0.0};
        CobylaExitStatus result = Cobyla.FindMinimum(calcfc, 2, 3, x, rhobeg, rhoend, iprint, maxfun);
        System.out.println(x);

    }
}