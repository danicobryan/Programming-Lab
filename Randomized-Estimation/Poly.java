public class Poly implements Function {
    @Override
    public double eval(double[] coefficients, double x) {
        double result = 0;
        for(int i = 0; i < coefficients.length; i++){
            result += coefficients[i]*(Math.pow(x,i));
        }
        return result;
    }
}
