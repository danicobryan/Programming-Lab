public class Circle extends Shape{
	private Point center;
	private double radius;

	public Circle(Point center, double radius){
		this.center = center;
		this.radius = radius;
	}



	@Override
	public boolean pointInArea(Point p){
		double distanceFromCenter = Math.sqrt(Math.pow(center.getX() - p.getX(), 2) + Math.pow(center.getY() - p.getY(), 2));
		return(distanceFromCenter <= radius);
	}


	public Point getCenter(){
		return this.center;
	}

	public double getRadius(){
		return this.radius;
	}

	@Override
	public double getXMin(){
		return center.getX() - radius;
	}

	@Override
	public double getXMax(){
		return center.getX() + radius;
	}

	@Override
	public double getYMin(){
		return center.getY() + radius;
	}

	@Override
	public double getYMax(){
		return center.getY() - radius;
	}

	
}
