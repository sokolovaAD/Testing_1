import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;
import static java.lang.Math.*;

public final class AtanCalculator {
    private static final double MIN_ERROR = 1.0E-7D;


    public static Double calculateAtan(double x, double maxError)  {
            if (abs(maxError) < MIN_ERROR || isNaN(maxError)) {
                return atanDecompose(x, getOptimalNLength(x, MIN_ERROR));
                }
            else if(isNaN(x)) {  return NaN; }
            else return atanDecompose(x, getOptimalNLength(x, maxError));
            }


        private static double atanDecompose(double x, int repeats) {
            if (Math.abs(x) > 1) {
                return signum(x) * PI/2 - atanDecompose(1 / x, repeats);
            } else {
                double result = 0.0D;
                for (int n=0;  n<=repeats; n++) {
                    result +=  pow(-1.0, n) * pow(x, (2 * n + 1)) / (2 * n + 1);
                    }
                return result;
                }
            }


        private static int getOptimalNLength(double x, double maxError) {
            if (abs(x) > 1) {
                return getOptimalNLength(1/x, maxError);
            }
            else {
                int min = 0;
                int max = Integer.MAX_VALUE;
                int n = 0;

                while(min < max) {
                    n = min / 2 + max / 2;
                    double trial = pow(x,(2 * n + 1)) / (2 * n + 1);
                    if (abs(trial) > abs(maxError)) {
                        min = n + 1;
                    } else {
                        max = n;
                    }
                }
                return n;
            }
        }

    }