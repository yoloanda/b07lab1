import java.lang.Math;

public class Polynomial {

    // fields
    double[] coefficients;

    // constructors
    public Polynomial() {
        coefficients = new double[1];
        coefficients[0] = 0;
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = new double[coefficients.length];

        for(int i = 0; i < coefficients.length; i++) {
            this.coefficients[i] = coefficients[i];
        }
    }

    // methods
    public Polynomial add(Polynomial p) {
        double[] sum;

        if (coefficients.length < p.coefficients.length)
            sum = new double[p.coefficients.length];
        else
            sum = new double[coefficients.length];

        for(int i = 0; i < sum.length; i++) {
            sum[i] = 0;

            if(i < coefficients.length)
                sum[i] += coefficients[i];

            if(i < p.coefficients.length)
                sum[i] += p.coefficients[i];
        }

        return new Polynomial(sum);
    }

    public double evaluate(double x) {
        double result = 0;

        for(int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }

        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}