public class Sin implements Function {
    @Override
    public double eval(double[] coefficients, double x) {
        return Math.sin(x);
    }
}
