public class DateDistance{
	public static void main(String[]args){
		long month0 = Long.parseLong(args[0]);
		long day0 = Long.parseLong(args[1]);
		long year0 = Long.parseLong(args[2]);
		long month1 = Long.parseLong(args[3]);
		long day1 = Long.parseLong(args[4]);
		long year1 = Long.parseLong(args[5]);
		System.out.println(distance (month0, day0, year0, month1, day1, year1));
	}
	
	public static boolean isCommonYear(long year){
		return ((year % 100 == 0 || year % 4 != 0) && year % 400 != 0);
	}
	
	public static long monthLength(long month, long year){
		long days = 0;
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
			days = 31;
		}
		if(month == 4 || month == 6 || month == 9 || month == 11){
			days = 30;
		}
		if(month == 2 && isCommonYear(year)){
			days = 28;
		}
		if(month == 2 && !isCommonYear(year)){
			days = 29;
		}
		return days;
	}
	
	public static boolean isRealDate (long month, long day, long year){
		return ((day <= monthLength(month, year) && day > 0) && (month > 0 && month <= 12) && year >= 0);
		
	}
	
	 public static long distance (long month0, long day0, long year0, long month1, long day1, long year1){
	 	long result = 0;
	 	long daysPerYear0 = 0;
	 	long daysPerYear1 = 0;
	 	long daysPerMonths0 = 0;
	 	long daysPerMonths1 = 0;
	 	
	 	for (long x = 1; x < year0; x++){
	 		if(isCommonYear(x)){
	 			daysPerYear0 = daysPerYear0 + 365;
	 		}
	 		if(!isCommonYear(x)){
	 			daysPerYear0 = daysPerYear0 + 366;
	 		}
	 	}
	 	
	 	for (long y = 1; y < year1; y++){
	 		if(isCommonYear(y)){
	 			daysPerYear1 = daysPerYear1 + 365;
	 		}
	 		if(!isCommonYear(y)){
	 			daysPerYear1 = daysPerYear1 + 366;
	 		}
	 	}
	 	
	 	for(long m = 1; m < month0; m++){
	 		daysPerMonths0 = daysPerMonths0 + monthLength(m, year0);
	 	}
	 	
	 	for(long n = 1; n < month1; n++){
	 		daysPerMonths1 = daysPerMonths1 + monthLength(n, year1);
	 	}
	 	
	 	if(isRealDate(month0, day0, year0) && isRealDate(month1, day1, year1)){
	 		long initialDays = daysPerMonths0 + day0 + daysPerYear0;
	 		long finalDays = daysPerMonths1 + day1 + daysPerYear1;
	 		result = Math.abs(finalDays - initialDays);
	 	}
	 	return result;
	 }
}
