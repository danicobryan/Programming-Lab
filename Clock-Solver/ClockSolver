class ClockSolver{
	public static void main(String[]args) throws Exception{
		
		double slice = 60;
		
		try{
			slice = Double.parseDouble(args[0]);
		} catch (ArrayIndexOutOfBoundsException e){
			slice = 60;
			System.out.println("Missing Input: Time slice has been set to default of 60.0");
		}catch (Exception e){
			System.err.println("Invalid Input: Input must be a real number.");
			System.exit(-1);
		} 
		
		if(slice >= 1800 || slice <= 0){
			System.out.println("Invalid Input: Time slice must be a positive real number and less than 1800.0");
			System.exit(-1);
		}
		

		Clock c = new Clock(slice);		
		
		while(c.isStillRunning()){
			c.increaseAngle();
			c.tick();
			if(c.formsLine()){
				System.out.println(c.toString());
			}
		}				
	}
}
