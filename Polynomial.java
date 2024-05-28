import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.util.Scanner;

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

    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = new double[coefficients.length];
        this.exponents = new int[exponents.length];

        for (int i = 0; i < coefficients.length; i++)
            this.coefficients[i] = coefficients[i];

        for (int i = 0; i < exponents.length; i++)
            this.exponents[i] = exponents[i];
    }

    public Polynomial(File file) throws FileNotFoundException {
        String poly;
        Scanner in = new Scanner(file);
        int index = 0;

        poly = in.nextLine();
        in.close();

        poly = poly.replace("-", "+-");
        String[] split = poly.split("\\+");

        coefficients = new double[split.length];
        exponents = new int[split.length];

        for (int i = 0; i < split.length; i++) {
            String[] term = split[i].split("x");

            coefficients[index] = Double.parseDouble(term[0]);

            if (term.length > 1)
                exponents[index] = Integer.parseInt(term[1]);
            else
                exponents[index] = 0;

            index += 1;
        }
    }

    // methods
    public void saveToFile(String fileName) throws IOException {
        File file = new File(fileName);
        String str = "";

        for (int i = 0; i < exponents.length; i++) {
            str = str.concat(String.valueOf(coefficients[i]));
            str = str.concat("x");
            str = str.concat(String.valueOf(exponents[i]));

            if (i != exponents.length - 1 && coefficients[i + 1] > 0)
                str = str.concat("+");
        }

        if (file.createNewFile()) {
            FileWriter output = new FileWriter(file, false);
            output.write(str);
            output.close();
        }
    }

    public Polynomial add(Polynomial p) {
        double[] sumCoefficients;
        int[] sumExponents;
        int length = -1;
        int index = 0;

        length = sumLength(p);
        sumCoefficients = new double[length];
        sumExponents = new int[length];

        for (int i = 0; i < length; i++)
            sumExponents[i] = -1;

        for (int i = 0; i < exponents.length; i++)
            if (!inArray(sumExponents, exponents[i])) {
                sumExponents[index] = exponents[i];
                sumCoefficients[index] = coefficients[i];
                index += 1;
            }
            else
                sumCoefficients[getIndex(sumExponents, exponents[i])] += coefficients[i];

        for (int i = 0; i < p.exponents.length; i++)
            if (!inArray(sumExponents, p.exponents[i])) {
                sumExponents[index] = p.exponents[i];
                sumCoefficients[index] = p.coefficients[i];
                index += 1;
            }
            else
                sumCoefficients[getIndex(sumExponents, p.exponents[i])] += p.coefficients[i];

        return new Polynomial(sumCoefficients, sumExponents);
    }

    public Polynomial multiply(Polynomial p) {
        double[] prodCoefficients;
        int[] prodExponents;
        int length = -1;
        int index = 0;
        int prodExp = -1;
        double prodCoef = -1;

        length = productLength(p);
        prodCoefficients = new double[length];
        prodExponents = new int[length];

        for (int i = 0; i < length; i++)
            prodExponents[i] = -1;

        for (int i = 0; i < exponents.length; i++)
            for (int j = 0; j < p.exponents.length; j++) {
                prodExp = exponents[i] + p.exponents[j];
                prodCoef = coefficients[i] * p.coefficients[j];

                if (!inArray(prodExponents, prodExp)) {
                    prodExponents[index] = prodExp;
                    prodCoefficients[index] = prodCoef;
                    index += 1;
                }
                else
                    prodCoefficients[getIndex(prodExponents, prodExp)] += prodCoef;
            }

        return new Polynomial(prodCoefficients, prodExponents);
    }

    public double evaluate(double x) {
        double result = 0;

        for (int i = 0; i < coefficients.length; i++)
            result += coefficients[i] * Math.pow(x, exponents[i]);

        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

    // helper function to determine length of the sum of polynomials
    private int sumLength(Polynomial p) {
        int length = exponents.length + p.exponents.length;

        for (int i = 0; i < p.exponents.length; i++)
            if (inArray(exponents, p.exponents[i]))
                length -= 1;

        return length;
    }

    // helper function to determine length of the product of polynomials
    private int productLength(Polynomial p) {
        int prodExp = -1;
        int length = -1;
        int index = 0;
        int[] exp;

        length = exponents.length * p.exponents.length;
        exp = new int[length];

        for (int i = 0; i < length; i++)
            exp[i] = -1;

        for (int i = 0; i < exponents.length; i++)
            for (int j = 0; j < p.exponents.length; j++) {
                prodExp = exponents[i] + p.exponents[j];

                if (!inArray(exp, prodExp)) {
                    exp[index] = prodExp;
                    index += 1;
                }
            }

        return index;
    }

    // helper function to determine if element is in array
    private boolean inArray(int[] array, int x) {
        for (int i = 0; i < array.length; i++)
            if (x == array[i])
                return true;

        return false;
    }

    // helper function to get index of element in array
    private int getIndex(int[] array, int x) {
        for (int i = 0; i < array.length; i++)
            if (x == array[i])
                return i;

        return -1;
    }
}