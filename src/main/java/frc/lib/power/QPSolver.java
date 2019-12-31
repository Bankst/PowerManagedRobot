package frc.lib.power;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.InitialGuess;
import org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunction;

import com.manyangled.gibbous.optim.convex.*;

public class QPSolver {
    public static void main(String[] args) {
        // create a convex objective function
        QuadraticFunction q = new QuadraticFunction(
            new double[][] { { -0.3, 0.0 }, { 0.0, -0.6 } },
            new double[] { 0.0, 0.0 },
            0.0);

        // optimize function q with an inequality constraint and an equality constraint,
        // using the barrier method
        BarrierOptimizer barrier = new BarrierOptimizer();
        PointValuePair pvp = barrier.optimize(
            new ObjectiveFunction(q),

            new LinearInequalityConstraint(
                new double[][] { { 140.0, 30.0 }, { 1.0, 0.0 }, { 0.0, 1.0 } }, // constraint x > 1,
                new double[] { 120.0, 1.0, 1.0 })
            
            );

        double[] xmin = pvp.getFirst();  // { 1.0, 1.0 }
        double vmin = pvp.getSecond();   // 1.0
        
        System.out.println(xmin[0]);
        System.out.println(xmin[1]);
        System.out.println(vmin);
    }
}