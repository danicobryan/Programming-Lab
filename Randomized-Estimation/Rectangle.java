public class Rectangle{
	private double x1, y1, x2, y2;

	
	public Rectangle(double x1, double y1, double x2, double y2){
		//this(new Point(0, 0), new Point(1, 1));
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	public double getX1(){
		return this.x1;
	}

	public double getX2(){
		return this.x2;
	}

	public double maxX(){
		if(this.x1 > this.x2){
			return this.x1;
		}else{
			return this.x2;
		}
	}

	public double minX(){
		if(this.x1 < this.x2){
			return this.x1;
		}else{
			return this.x2;
		}
	}

	public double maxY(){
		if(this.y1 > this.y2){
			return this.y1;
		}else{
			return this.y2;
		}
	}

	public double minY(){
		if(this.y1 < this.y2){
			return this.y1;
		}else{
			return this.y2;
		}
	}

	public double getWidth(){
		return Math.abs(this.x2 - this.x1);
	}

	public double getHeight(){
		return Math.abs(this.y2 - this.y1);
	}

	public double getArea(){
		return this.getHeight() * this.getWidth();
	}

	/*public cornerAt(double x, double y){

	}*/


}
