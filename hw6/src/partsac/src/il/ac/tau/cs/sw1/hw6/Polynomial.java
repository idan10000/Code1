package partsac.src.il.ac.tau.cs.sw1.hw6;


import java.util.Arrays;

public class Polynomial {
    private double[] coefficients;

    /*
     * Creates the zero-polynomial with p(x) = 0 for all x.
     */
    public Polynomial() {
        this.coefficients = new double[0];
    }

    /*
     * Creates a new polynomial with the given coefficients.
     */
    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    /*
     * Adds this polynomial to the given polynomial and returns the sum as a new
     * polynomial.
     */
    public Polynomial add(Polynomial polynomial) {
        int maxLen = Math.max(polynomial.coefficients.length, coefficients.length);
        double[] newCoefficient = new double[maxLen];
        for (int i = 0; i < maxLen; i++) {
            if (i < coefficients.length)
                newCoefficient[i] += coefficients[i];
            if (i < polynomial.coefficients.length)
                newCoefficient[i] += polynomial.coefficients[i];
        }
        int lastCoefficent = newCoefficient.length - 1;
        while (newCoefficient[lastCoefficent--] == 0.0) ;
        return new Polynomial(Arrays.copyOfRange(newCoefficient, 0, lastCoefficent + 1));
    }

    /*
     * Multiplies this polynomial by c and returns the result as a new polynomial.
     */
    public Polynomial multiplyByConstant(double c) {
        double[] newCoefficient = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            newCoefficient[i] = c * coefficients[i];
        }
        return new Polynomial(newCoefficient);
    }

    /*
     * Multiplies this polynomial by the given polynomial and returns the result as
     * a new polynomial.
     */
    public Polynomial multiply(Polynomial polynomial) {
        double[] newCoefficient = new double[coefficients.length + polynomial.coefficients.length];
        for (int i = 0; i < polynomial.coefficients.length; i++) {
            for (int j = 0; j < coefficients.length; j++) {
                newCoefficient[i + j] += coefficients[j] * polynomial.coefficients[i];
            }
        }
        return new Polynomial(newCoefficient);
    }

    /*
     * Returns the degree (the largest exponent) of this polynomial.
     */
    public int getDegree() {
        return coefficients.length;
    }

    /*
     * Returns the coefficient of the variable x with degree n in this polynomial.
     */
    public double getCoefficient(int n) {
        if(n < getDegree() && n >= 0)
            return coefficients[n];
        return 0;
    }

    /*
     * set the coefficient of the variable x with degree n to c in this polynomial.
     * If the degree of this polynomial < n, it means that that the coefficient of
     * the variable x with degree n was 0, and now it will change to c.
     */
    public void setCoefficient(int n, double c) {
        if(n < getDegree())
            coefficients[n] = c;
        else{
            double[] newCoefficients = new double[n+1];
            for (int i = 0; i < getDegree(); i++) {
                newCoefficients[i] = coefficients[i];
                newCoefficients[n] = c;
            }
            this.coefficients = newCoefficients;
        }
    }

    /*
     * Returns the derivative of this polynomial. The first derivative of a
     * polynomial a0x0 + ... + anxn is defined as 1 * a1x0 + ... + n anxn-1.
     *
     */
    public Polynomial getFirstDerivative() {
        double[] newCoefficients = new double[getDegree() - 1];
        for (int i = 0; i < newCoefficients.length; i++) {
            newCoefficients[i] = i + i * coefficients[i+1];
        }
        return new Polynomial(newCoefficients);
    }

    /*
     * given an assignment for the variable x, compute the value of the polynomial
     */
    public double computePolynomial(double x) {
        double sum = 0;
        for (int i = 0; i < coefficients.length; i++) {
            sum += coefficients[i] * Math.pow(x,i);
        }
        return sum;
    }

    /*
     * given an assignment for the variable x, return true iff x is a root of this
     * polynomial
     */
    public boolean isARoot(double x) {
        return computePolynomial(x) == 0.0;
    }

}
