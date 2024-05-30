import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {

	public static void main(String[] args) {

		// coefficients
		double[] c1 = {7, 13, 4, -5};
		double[] c2 = {8, -4};
		double[] c3 = {21};
		double[] c4 = {-2, 1};
		double[] c5 = {-1, 2};

		// exponents
		int[] e1 = {5, 2, 1, 0};
		int[] e2 = {6, 1};
		int[] e3 = {3};
		int[] e4 = {1, 0};
		int[] e5 = {0, 1};


		// polynomials
		Polynomial zero = new Polynomial();
		Polynomial p1 = new Polynomial(c1, e1);
		//Polynomial p1 = null;

//		try {
//			p1 = new Polynomial(new File("poly.txt"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}

		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial p3 = new Polynomial(c3, e3);
		Polynomial p4 = new Polynomial(c4, e4);
		Polynomial p5 = new Polynomial(c5, e5);

		Polynomial sum1 = zero.add(p1);
		Polynomial sum2 = p2.add(zero);
		Polynomial sum3 = sum1.add(sum2);
		Polynomial sum4 = p3.add(sum2);
		Polynomial sum5 = sum2.add(p4);
		Polynomial sum6 = p4.add(p5);

		System.out.println("Evaluate 7x^5+13x^2+4x-5 @ 2.3 - Expected value: 523.5140; Actual value: " + sum1.evaluate(2.3));
		System.out.println("Evaluate 8x^6-4x @ -1.4 - Expected value: 65.8363; Actual value: " + sum2.evaluate(-1.4));
		System.out.println("Evaluate 8x^6+7x^5+13x^2 - 5 @ -0.5 - Expected value: -1.8438; Actual value: " + sum3.evaluate(-0.5));
		System.out.println("Evaluate 8x^6+21x^3-4x @ 1.1 - Expected value: 37.7235; Actual value: " + sum4.evaluate(1.1));
		System.out.println("Evaluate 8x^6-6x+1 @ -1.3 - Expected value: 47.4145; Actual value: " + sum5.evaluate(-1.3));
		System.out.println("Evaluate 0 @ 10 - Expected value: 0.0; Actual value: " + sum6.evaluate(10));

		Polynomial prod1 = p1.multiply(p2);
		Polynomial prod2 = p3.multiply(p5);
		Polynomial prod3 = p5.multiply(p4);

		System.out.println("Evaluate 0 @ 14 - Expected value: 0.0; Actual value: " + zero.multiply(p3).evaluate(0));
		System.out.println("Evaluate 0 @ 0 - Expected value: 0.0; Actual value: " + p3.multiply(zero).evaluate(0));
		System.out.println("Evaluate 56x^11+104x^8+32x^7-68x^6-52x^3-16x^2+20x @ -0.3 - Expected value: -6.0858; Actual value: " + prod1.evaluate(-0.3));
		System.out.println("Evaluate 42x^4-21x^3 @ 3.5 - Expected value: 5402.25; Actual value: " + prod2.evaluate(3.5));
		System.out.println("Evaluate -4x^2+4x-1 @ -1.2 - Expected value: -11.56; Actual value: " + prod3.evaluate(-1.2));

        if (p4.hasRoot(0.5))
            System.out.println("0.5 is a root of -2x+1");
        else
            System.out.println("0.5 is not a root of -2x+1");

        if (p2.hasRoot(3))
            System.out.println("3 is a root of 8x^6-4x");
        else
            System.out.println("3 is not a root of 8x^6-4x");

        try {
			zero.saveToFile("zero_poly.txt");
			prod1.saveToFile("polynomial1.txt");
			p3.saveToFile("polynomial2.txt");
		} catch (IOException e) {
			e.printStackTrace();
        }

	}

}