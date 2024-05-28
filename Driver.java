import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {

    public static void main(String [] args) {

       // test constructor w no parameters
        Polynomial empty_poly = new Polynomial();
        System.out.println("Evaluate polynomial 0x^0 @ 3: Expected value: 0; Actual value: " + empty_poly.evaluate(3));

        double[] c1 = {7, 3};
        double[] c2 = {4, 8, 5};
        //double[] c3 = {19, -13, 4};
        double[] c4 = {-2};

        int[] e1 = {2, 0};
        int[] e2 = {3, 2, 1};
        //int[] e3 = {10, 7, 1};
        int[] e4 = {7};

        Polynomial p1 = new Polynomial(c1, e1);
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial p3 = null;

		try { // test constructor w file parameter
			p3 = new Polynomial(new File("poly.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        Polynomial p4 = new Polynomial(c4, e4);

        // test add method
        Polynomial sum1 = p1.add(p2);
        Polynomial sum2 = p3.add(p4);
        Polynomial sum3 = p2.add(p3);

        System.out.println("Evaluate 4x^3+15x^2+5x+3 @ 2.7: Expected value: 204.582; Actual value: " + sum1.evaluate(2.7));
        System.out.println("Evaluate 19x^10-15x^7+4x @ 1.3: Expected value: 173.0084; Actual value: " + sum2.evaluate(1.3));
        System.out.println("Evaluate 19x^10-13x^7+4x^3+8x^2+9x @ -1.1: Expected value: 69.0704; Actual value: " + sum3.evaluate(-1.1));

        // test multiply method
        Polynomial prod1 = p2.multiply(p1);
        Polynomial prod2 = p4.multiply(p3);
        Polynomial prod3 = p3.multiply(p2);

        System.out.println("Evaluate 28x^5+56x^4+47x^3+24x^2+15x @ 1: Expected value: 170; Actual value: " + prod1.evaluate(1));
        System.out.println("Evaluate -38x^17+26x^14-8x^8 @ -1: Expected value: 56; Actual value: " + prod2.evaluate(-1));
        System.out.println("Evaluate 76x^13-52x^10+16x^4+152x^12-104x^9+32x^3+95x^11-65x^8+20x^2 @ 1: Expected value: 170; Actual value: " +  prod3.evaluate(1));

        // test hasRoot method
        if (p1.hasRoot(-2))
            System.out.println("-2 is a root of 7x^2+3");
        else
            System.out.println("-2 is not a root of 7x^2+3");

        if (sum2.hasRoot(0))
            System.out.println("0 is a root of 19x^10-15x^7+4x");
        else
            System.out.println("0 is not a root of 19x^10-15x^7+4x");

        // test saveToFile method
        try {
			empty_poly.saveToFile("polynomial1.txt");
			prod3.saveToFile("polynomial2.txt");
		} catch (IOException e) {
			e.printStackTrace();
        }
    }
}
