import java.util.Random;

public class RandomizedIntegrator{

	private Function f;
	private double a, b;
	private double[] coefficients;
	
	public static void main(String[] args){

		try{
			Function f = (Function)Class.forName(args[0]).newInstance();
			double a = Double.parseDouble(args[args.length - 2]);
			double b = Double.parseDouble(args[args.length - 1]);
			double[] coefficients = new double[args.length - 3];

			for(int i = 0; i < coefficients.length; i++){
				coefficients[i] = Double.parseDouble(args[i + 1]);
			}

            RandomizedIntegrator integrator = new RandomizedIntegrator (f, a, b);
            
            Rectangle box = integrator.BoundingBox(f, coefficients, a, b);

            System.out.println("Area: " + integrator.generatePoints(f, coefficients, box));



		} catch (ClassNotFoundException e) {
            System.err.println(args[0] + " is not defined");
        } catch (ClassCastException e) {
            System.err.println(args[0] + " does not implement SimpleFunction");
        } catch (IllegalAccessException | InstantiationException e) {
            System.err.println(args[0] + " is not public or is malformed");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Syntax: java FunctionTablePrinter name a b");
        }
		
	}



	public RandomizedIntegrator(Function f, double min, double max){
		this.f = f;
		this.a = min;
		this.b = max;
	}



	public Rectangle BoundingBox(Function f, double[] coefficients, double xMin, double xMax){
		double buffer = 1;
		double yMin = getYMin(f, coefficients, xMin, xMax) - buffer;
		double yMax = getYMax(f, coefficients, xMin, xMax) + buffer;
		Rectangle result = new Rectangle(xMin, yMax, xMax, yMin);
		return result;
	}

	public double getYMax(Function f, double[] coefficients, double xMin, double xMax){
		double maximum = f.eval(coefficients, xMin);
		double numberOfPoints = 1000000;
		double increment = (xMax - xMin)/numberOfPoints;

		for(double i = xMin; i <= xMax; i += increment){
			double currentValue = f.eval(coefficients, i);
			if(currentValue > maximum){
				maximum = currentValue;
			}
		}
		return maximum;
	}

	public double getYMin(Function f, double[] coefficients, double xMin, double xMax){
		double minimum = f.eval(coefficients, xMin);
		double numberOfPoints = 1000000;
		double increment = (xMax - xMin)/numberOfPoints;

		for(double i = xMin; i <= xMax; i += increment){
			double currentValue = f.eval(coefficients, i);
			if(currentValue < minimum){
				minimum = currentValue;
			}
		}
		return minimum;
	}

	public double generatePoints(Function f, double[] coefficients, Rectangle box){
		double pointsInRange = 0;
		double numberOfDarts = 1000000;
		Random rand = new Random();
		for(int i = 0; i < numberOfDarts; i++){
			double x = (box.maxX() - box.minX()) * rand.nextDouble() + box.minX();
			double y = (box.maxY() - box.minY()) * rand.nextDouble() + box.minY();
			if(f.eval(coefficients, x) > 0 && y > 0){
				if(y < f.eval(coefficients, x)){
					pointsInRange++;
				}
			}
			if(f.eval(coefficients, x) < 0 && y < 0){
				if(y > f.eval(coefficients, x)){
					pointsInRange--;
				}
			}
		}
		return (pointsInRange / numberOfDarts) * box.getArea();
	}

	

}
