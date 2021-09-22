import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;
import static java.lang.Math.*;

public final class AtanCalculator {
    private static final double MIN_ERROR = 1.0E-7D;


    public static Double calculateAtan(double x, double maxError)  {
        if (abs(maxError) < MIN_ERROR || isNaN(maxError)) {
            return atanDecompose(x, MIN_ERROR);
        }
        else if(isNaN(x)) {  return NaN; }
        else return atanDecompose(x, maxError);
    }


    private static double atanDecompose(double x, double repeats) {
        if (abs(x) > 1) {
            return signum(x) * PI/2 - atanDecompose(1 / x, repeats);
        } else {
            boolean stop = false;
            double result = 0.0D;
            int n=0;
            while(!stop) {
                result +=  pow(-1.0, n) * pow(x, (2 * n + 1)) / (2 * n + 1);
                double trial = pow(x,(2 * n + 1)) / (2 * n + 1);
                if (abs(trial) > abs(repeats)) {
                    n+=1;
                }
                else stop = true;
            }
            return result;
        }
    }
}