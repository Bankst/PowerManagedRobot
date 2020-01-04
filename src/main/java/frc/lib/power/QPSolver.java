package frc.lib.power;

import com.cureos.numerics.Calcfc;
import com.cureos.numerics.Cobyla;
import com.cureos.numerics.CobylaExitStatus;

public class QPSolver {

    public static void main(String[] args) {
        double rhobeg = 0.2;
        double rhoend = 1.0e-10;
        int iprint = 0;
        int maxfun = 50;

        /* Max Allowable = 120 */
        /* Requested Subsystem_1(x[0]) = 140 */
        /* Requested Subsystem_2(x[1]) = 100 */
        /* Requested Subsystem_3(x[2]) = 140 */
        /* Requested Subsystem_4(x[3]) = 100 */
        /* Weight Subsystem_1(x[0]) = 0.3 */
        /* Weight Subsystem_2(x[1]) = 0.6 */
        /* Weight Subsystem_3(x[2]) = 0.4 */
        /* Weight Subsystem_4(x[3]) = 0.6 */

        long processStartNanos = System.nanoTime();
        Calcfc calcfc = new Calcfc() {
            @Override
            public double Compute(int n, int m, double[] x, double[] con) {
                con[0] = 120 - ((140 * x[0]) + (100 * x[1]) + (140 * x[2]) + (100 * x[3]) + (140 * x[4]) + (100 * x[5]) + (140 * x[6]) + (100 * x[7])); /* 140*x_0 + 100*x_1 + 140*x_2 + 100*x_3 <= 120 */
                con[1] = 1 - x[0]; /* x_0 <= 1 */
                con[2] = 1 - x[1]; /* x_1 <= 1 */
                con[3] = 1 - x[2]; /* x_2 <= 1 */
                con[4] = 1 - x[3]; /* x_3 <= 1 */
                con[5] = 1 - x[4]; /* x_0 <= 1 */
                con[6] = 1 - x[5]; /* x_1 <= 1 */
                con[7] = 1 - x[6]; /* x_2 <= 1 */
                con[8] = 1 - x[7]; /* x_3 <= 1 */
                con[9] = x[0] - 0; /* 0 <= x_0 */
                con[10] = x[1] - 0; /* 0 <= x_1 */
                con[11] = x[2] - 0; /* 0 <= x_2 */
                con[12] = x[3] - 0; /* 0 <= x_s3 */
                con[13] = x[4] - 0; /* 0 <= x_0 */
                con[14] = x[5] - 0; /* 0 <= x_1 */
                con[15] = x[6] - 0; /* 0 <= x_2 */
                con[16] = x[7] - 0; /* 0 <= x_s3 */
                
                /* -0.3 * x_0^2 + -0.6 * x_1^2 + -0.4 * x_2^2 + -0.6 * x_3^2 */
                return -0.3 * Math.pow(x[0], 2.0) + -0.6 * Math.pow(x[1], 2.0) + -0.4 * Math.pow(x[2], 2.0) + -0.6 * Math.pow(x[3], 2.0) + -0.3 * Math.pow(x[4], 2.0) + -0.6 * Math.pow(x[5], 2.0) + -0.4 * Math.pow(x[6], 2.0) + -0.6 * Math.pow(x[7], 2.0);
            }
        };
        double[] x = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        /* 4 variables(subsystems) and 9 constraints */
        CobylaExitStatus result = Cobyla.FindMinimum(calcfc, 8, 17, x, rhobeg, rhoend, iprint, maxfun);
        long processTime = System.nanoTime() - processStartNanos;
        System.out.println(x);
        System.out.printf("%.2f ms", processTime / 1000000.0);

    }
}