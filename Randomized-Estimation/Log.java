public class Log implements Function {
    @Override
    public double eval(double[] coefficients, double x) {
        return Math.log(x);
    }
}
