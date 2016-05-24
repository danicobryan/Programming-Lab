import java.util.Random;

public class RandomizedEstimator{
	
	private Shape[] shapes;

	
	public static void main(String[] args){
		int j = 0;
		int lengthOfShapeArray = 0;
		while(j <= args.length - 1){
			if(args[j].equals("Circle")){
				lengthOfShapeArray++;
				j += 4;
				if(j >= (args.length - 1)){
					break;
				}
			}
			
			if(args[j].equals("Triangle")){
				lengthOfShapeArray++;
				j += 7;
				if(j >= (args.length - 1)){
					break;
				}
			}
		}
		System.out.println("Shapes in Array: " + lengthOfShapeArray);

		Shape[] shapes = new Shape[lengthOfShapeArray];

		int i = 0;
		int indexOfShape = 0;
		while (i <= args.length - 1) {
			if(args[i].equals("Circle")){
				Point p = new Point(Double.parseDouble(args[i+1]), Double.parseDouble(args[i+2]));
				double radius = Double.parseDouble(args[i+3]);
				shapes[indexOfShape] = new Circle(p, radius);
				i += 4; 
				indexOfShape++;
				if(i >= (args.length - 1)){
					break;
				}
			}
			if(args[i].equals("Triangle")){
				Point a = new Point(Double.parseDouble(args[i+1]), Double.parseDouble(args[i+2]));
				Point b = new Point(Double.parseDouble(args[i+3]), Double.parseDouble(args[i+4]));
				Point c = new Point(Double.parseDouble(args[i+5]), Double.parseDouble(args[i+6]));
				shapes[indexOfShape] = new Triangle(a, b, c);
				i += 7;
				indexOfShape++;
				if(i >= (args.length - 1)){
					break;
				}
			}
			
		}

		RandomizedEstimator estimator = new RandomizedEstimator(shapes);
		//System.out.println(java.util.Arrays.toString(shapes));
		Rectangle box = estimator.BoundingBox(shapes);
		




		for(int k = 0; k < shapes.length; k++){
			System.out.println("Area: " + estimator.areaOfSingleShape(shapes[k], box));
		}
		
		System.out.println("Overlap Area: " + estimator.areaOfOverlap(shapes, box));

		System.out.println("Union Area: " + estimator.areaOfUnion(shapes, box));

		System.out.println("Area of Shapes that do not Intersect: " + estimator.areaOfNonIntersectionShapes(shapes, box));

	}


	public RandomizedEstimator(Shape[] shapes){
		this.shapes = shapes;
	}

	public Rectangle BoundingBox(Shape[] shapes){
		
		double xMin = shapes[0].getXMin();
		double xMax = shapes[0].getXMax();
		double yMin = shapes[0].getYMin();
		double yMax = shapes[0].getYMax();
		for(int i = 0; i < shapes.length; i++){
			if(shapes[i].getXMin() < xMin){
				xMin = shapes[i].getXMin();
			}
			if(shapes[i].getXMax() > xMax){
				xMax = shapes[i].getXMax();
			}
			if(shapes[i].getYMin() < yMin){
				yMin = shapes[i].getYMin();
			}
			if(shapes[i].getYMax() > yMax){
				yMax = shapes[i].getYMax();
			}
		}
		return new Rectangle(xMax, yMax, xMin, yMin);
	}

	public double areaOfSingleShape(Shape s, Rectangle box){
		double pointsInShape = 0;
		double numberOfDarts = 1000000;
		Random rand = new Random();
		double area = 0;
		//double[] areasOfShapes = new double[shapes.length]; 

		
		for(int j = 0; j < numberOfDarts; j++){
			double x = (box.maxX() - box.minX()) * rand.nextDouble() + box.minX();
			double y = (box.maxY() - box.minY()) * rand.nextDouble() + box.minY();
			Point p = new Point(x, y);
			if(s.pointInArea(p)){
				pointsInShape++;
			}
		}			
		return (pointsInShape / numberOfDarts) * box.getArea();
	}

	


	public double areaOfOverlap(Shape[] shapes, Rectangle box){
		double pointsInOverlap = 0;
		double numberOfDarts = 1000000;
		Random rand = new Random();

		for(int i = 0; i < numberOfDarts; i++){
			double x = (box.maxX() - box.minX()) * rand.nextDouble() + box.minX();
			double y = (box.maxY() - box.minY()) * rand.nextDouble() + box.minY();
			Point p = new Point(x, y);
			double pointsInCommon = 0;
			for(int j = 0; j <= shapes.length - 1; j++){
				if(shapes[j].pointInArea(p)){
					pointsInCommon++;
					if(pointsInCommon == shapes.length){
						pointsInOverlap++;
					}
				}
			}
				
		}
		return (pointsInOverlap / numberOfDarts) * box.getArea();
	}



	public double areaOfUnion(Shape[] shapes, Rectangle box){
		double pointsInArea = 0;
		double numberOfDarts = 1000000;
		Random rand = new Random();

		for(int i = 0; i < numberOfDarts; i++){
			double x = (box.maxX() - box.minX()) * rand.nextDouble() + box.minX();
			double y = (box.maxY() - box.minY()) * rand.nextDouble() + box.minY();
			Point p = new Point(x, y);
			for(int j = 0; j <= shapes.length - 1; j++){
				if(shapes[j].pointInArea(p)){
					pointsInArea++;
					break;
				}
			}
		}
		return (pointsInArea / numberOfDarts) * box.getArea();
	}

	public double areaOfNonIntersectionShapes(Shape[] shapes, Rectangle box){
		double pointsInArea = 0;
		double numberOfDarts = 1000000;
		Random rand = new Random();

		for(int i = 0; i < numberOfDarts; i++){
			double x = (box.maxX() - box.minX()) * rand.nextDouble() + box.minX();
			double y = (box.maxY() - box.minY()) * rand.nextDouble() + box.minY();
			Point p = new Point(x, y);
			double pointsInCommon = 0;
			for(int j = 0; j <= shapes.length - 1; j++){
				if(shapes[0].pointInArea(p)){
					pointsInCommon++;
					if(pointsInCommon == shapes.length){
						pointsInArea++;
					}
				}
			}

		}
		System.out.println(pointsInArea);
		return (pointsInArea / numberOfDarts) * box.getArea();
	}
}
