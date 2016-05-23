class ClockSolver2{
	public static void main(String[]args) throws Exception{
		double slice = 60;
		double angleInput = 180;		
	
		try{
			slice = Double.parseDouble(args[0]);
			angleInput = Double.parseDouble(args[1]);
		} catch (Exception e){
			System.err.println("Invalid Input: Input must be a real number.");
			System.exit(-1);
		}
		
		if(slice >= 1800 || slice <= 0){
			System.out.println("Invalid Input: Time slice must be a positive real number and less than 1800.0");
			System.exit(-1);
		}
		
		if(angleInput >= 360 || angleInput <= 0){
			System.out.println("Invalid Input: The angle must be a positive real number and less than 360.0");
			System.exit(-1);
		}
			
		Clock2 c = new Clock2(slice, angleInput);		
		
		while(c.isStillRunning()){
			c.increaseAngle();
			c.tick();
			if(c.formsAngleInput()){
				System.out.println(c.toString());
			}
		}
	}
}
