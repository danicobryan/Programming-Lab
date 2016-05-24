public class Exp implements Function {
    @Override
    public double eval(double[] coefficients, double x) {
        return Math.exp(x);
    }
}
