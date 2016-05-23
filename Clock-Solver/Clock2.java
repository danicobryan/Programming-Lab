class Clock2{

	double hours;
	double minutes;
	double seconds;
	double slice;
	double angle;
	double angleInput;
	
	public Clock2(double slice, double angleInput){
		this.angle = 0;
		this.slice = slice;
		this.angleInput = angleInput;
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
	}
	
	
	public void tick(){
		this.seconds += this.slice;
	}
	
	public double hours(){
		return this.hours;
	}

	public double minutes(){
		return this.minutes;
	}
	
	public double seconds(){
		return this.seconds;
	}
	

	public String toString(){
		double totalSeconds = this.seconds;
		this.hours = (this.seconds / 3600);
		this.minutes = Math.floor(this.seconds / 60) % 60;
		this.seconds = this.seconds % 60;
		
		String time = (int)this.hours + ":" + (int)this.minutes + ":" + this.seconds;
		
		this.seconds = totalSeconds;
		
		return time;
	}
	
	public double anglePerTick() {
		double SECONDS_PER_CYCLE = 3600;
		double minuteHand = 360 * (this.slice / SECONDS_PER_CYCLE);
		double secondHand = minuteHand / 12;
		return minuteHand - secondHand;
	}
	
	public void increaseAngle(){
		if(this.angle >= 360){
			this.angle = this.angle - 360;
		}
		this.angle = this.angle + this.anglePerTick();
	}
	
	public double getAngle(){
		return this.angle;
	}
	
	public boolean isStillRunning(){
		double SECONDS_PER_12HR_DAY = 43200;
		return this.seconds <= SECONDS_PER_12HR_DAY;
	}
	
	public boolean formsAngleInput(){
		return (((this.angle < (this.angleInput + (this.anglePerTick() / 2))) && ((this.angle > (this.angleInput - (this.anglePerTick() / 2))))) ||
		((360 - this.angle < (this.angleInput + (this.anglePerTick() / 2))) && ((360 - this.angle > (this.angleInput - (this.anglePerTick() / 2))))));
	}
}
