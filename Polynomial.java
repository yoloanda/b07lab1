import java.lang.Math;
import java.util.ArrayList;

public class Polynomial {

    // fields
    double[] coefficients;
    int[] exponents;

    // constructors
    public Polynomial() {
        coefficients = new double[1];
        exponents = new int[1];

        coefficients[0] = 0;
        exponents[0] = 0;
    }

    public Polynomial(File file) {
        String poly = "";
        Scanner in = new Scanner(file);

        poly = in.nextLine();

        /*
        go through string + add '+' before every '-'
        split string using delimiter '+'
        set length of poly = length of split strings
        go through each split string
            split string into using delimiter 'x'
                first element = coefficient
                if second element exists, second element = exponent
        */

    }

    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = new double[coefficients.length];
        this.exponents = new int[exponents.length];

        for(int i = 0; i < coefficients.length; i++)
            this.coefficients[i] = coefficients[i];

        for(int i = 0; i < exponents.length; i++)
            this.exponents[i] = exponents[i];
    }

    // methods
    public Polynomial add(Polynomial p) {
        double[] sum_coefficients;
        int[] sum_exponents;
        ArrayList<Integer> t_exp = new ArrayList<Integer>();
        int index = -1;

        for(int i = 0; i < min(exponents.length, p.exponents.length); i++) {
            if(!t_exp.contains(exponents[i]))
                t_exp.add(exponents[i]);

            if(!t_exp.contains(p.exponents[i]))
                t_exp.add(p.exponents[i]);
        }

        for(int i = min(exponents.length, p.exponents.length); i < max(exponents.length, p.exponents.length); i++) {
            if(exponents.length >= i && !t_exp.contains(exponents[i]))
                t_exp.add(exponents[i]);
            else if(!t_exp.contains(p.exponents[i]))
                t_exp.add(p.exponents[i]);
        }

        sum_exponents = t_exp.sort().toArray();
        sum_coefficients = new double[sum_exponents.length];

        for(int i = 0; i < exponents.length; i++) {
            index = t_exp.indexOf(exponents[i]);
            coefficients[index] += coefficients[i];
        }

        for(int i = 0; i < p.coefficients.length; i++) {
            index = t_exp.indexOf(p.exponents[i]);
            coefficients[index] += p.coefficients[i];
        }

        return new Polynomial(sum_coefficients, sum_exponents);
    }

    public Polynomial multiply(Polynomial p) {
        ArrayList<Integer> t_exp = new ArrayList<Integer>();
        ArrayList<Double> t_coeff = new ArrayList<Double>();
        int exp = -1;
        int index = -1;

        for(int i = 0; i < exponents.length; i++) {
            for(int j = 0; j < p.exponents.length; j++) {
                exp = exponents[i] * p.exponents[j];

                if(t_exp.contains(exp)) {
                    index = t_exp.indexOf(exp);
                    t_coeff.set(index, t_coeff.get(index) + coefficients[i] * p.coeffecients[j]);
                }

                else {
                    t_exp.add(exp);
                    t_coeff.add(coefficients[i] * p.coeffecients[j]);
                }
            }
        }

        return new Polynomial(t_coeff.toArray(), t_exp.toArray());
    }

    public double evaluate(double x) {
        double result = 0;

        for(int i = 0; i < coefficients.length; i++)
            result += coefficients[i] * Math.pow(x, exponents[i]);

        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}