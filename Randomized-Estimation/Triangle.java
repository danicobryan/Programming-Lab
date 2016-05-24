public class Triangle extends Shape{
	private Point a, b, c;


	public Triangle(Point a, Point b, Point c){
		this.a = a;
		this.b = b;
		this.c = c;
	}

	
	@Override
	public boolean pointInArea(Point p){
		
		double aX = this.a.getX(), aY = this.a.getY();
		double bX = this.b.getX(), bY = this.b.getY();
		double cX = this.c.getX(), cY = this.c.getY();
		double pX = p.getX(), pY = p.getY();

		//Takes area of four triangles using the point given
		double abc = Math.abs (aX * (bY - cY) + bX * (cY - aY) + cX * (aY - bY));
		double pab = Math.abs (aX * (bY - pY) + bX * (pY - aY) + pX * (aY - bY));
		double pac = Math.abs (aX * (pY - cY) + pX * (cY - aY) + cX * (aY - pY));
		double pbc = Math.abs (pX * (bY - cY) + bX * (cY - pY) + cX * (pY - bY));

		return (pab + pac + pbc == abc);
	}



	@Override
	public double getXMax(){
		double xMax = 0;
		if((this.a.getX() >= this.b.getX()) && (this.a.getX() >= this.c.getX())){
			xMax = this.a.getX();
		}
		if((this.b.getX() >= this.a.getX()) && (this.b.getX() >= this.c.getX())){
			xMax = this.b.getX();
		}
		if((this.c.getX() >= this.a.getX()) && (this.c.getX() >= this.b.getX())){
			xMax = this.c.getX();
		}
		return xMax;
	}

	@Override
	public double getXMin(){
		double xMin = 0;
		if((this.a.getX() <= this.b.getX()) && (this.a.getX() <= this.c.getX())){
			xMin = this.a.getX();
		}
		if((this.b.getX() <= this.a.getX()) && (this.b.getX() <= this.c.getX())){
			xMin = this.b.getX();
		}
		if((this.c.getX() <= this.a.getX()) && (this.c.getX() <= this.b.getX())){
			xMin = this.c.getX();
		}
		return xMin;
	}

	@Override
	public double getYMax(){
		double yMax = 0;
		if((this.a.getY() >= this.b.getY()) && (this.a.getY() >= this.c.getY())){
			yMax = this.a.getY();
		}
		if((this.b.getY() >= this.a.getY()) && (this.b.getY() >= this.c.getY())){
			yMax = this.b.getY();
		}
		if((this.c.getY() >= this.a.getY()) && (this.c.getY() >= this.b.getY())){
			yMax = this.c.getY();
		}
		return yMax;
	}

	@Override
	public double getYMin(){
		double yMin = 0;
		if((this.a.getY() <= this.b.getY()) && (this.a.getY() <= this.c.getY())){
			yMin = this.a.getY();
		}
		if((this.b.getY() <= this.a.getY()) && (this.b.getY() <= this.c.getY())){
			yMin = this.b.getY();
		}
		if((this.c.getY() <= this.a.getY()) && (this.c.getY() <= this.b.getY())){
			yMin = this.c.getY();
		}
		return yMin;
	}


}
