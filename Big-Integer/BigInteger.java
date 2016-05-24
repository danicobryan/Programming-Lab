import java.util.Arrays;

public class BigInteger{

	private String number;
	private int length;
	private int val;
	private int []digits;
	private boolean positive;
	
	
	public BigInteger(String val){
		int index = 0;
		this.positive = true;
		if(val.charAt(index) == '-'){
			this.positive = false;
			index += 1;
		}
		
		int sumOfDigits = 0;
		for(int i = 0; i < val.length(); i++){
			char c = val.charAt(i);
			sumOfDigits += Character.getNumericValue(c);
		}
		if(sumOfDigits == 0){
			this.positive = true;
			val = "0";
		}else{
			while(val.charAt(index) == '0'){
				index++;
			}
		}
		
		
		String newString = val.substring(index, val.length());
		
		index = 0;
		
		int length = newString.length();
		this.digits = new int[length];
		for(int i = length - 1; i >= 0; i--){
			char c = newString.charAt(i);
			//System.out.println("char: " + c);
			
			this.digits[index] = Character.getNumericValue(c);
			index++;
		}
		this.length = this.digits.length;
		
		//System.out.println(Arrays.toString(this.digits));
		//System.out.println(this.digits.length);
	}
	

	public int maxLength(int[] x, int[] y){
		if(x.length > y.length){
			return x.length;
		}else{
			return y.length;
		}
	}

	public int minLength(int[] x, int[] y){
		if(x.length < y.length){
			return x.length;
		}
		else{
			return y.length;
		}
	}


	public BigInteger add (BigInteger val){

		if(this.positive == val.positive){

			int shortestLength = minLength(this.digits, val.digits);
			int longestLength = maxLength(this.digits, val.digits);

			int[] sum = new int[shortestLength + 1];
			for(int i = 0; i < shortestLength; i++){
				sum[i] = this.digits[i] + val.digits[i];
			}
			
			int[] result = new int[longestLength + 1];
			for(int i = 0; i < shortestLength; i++){
				result[i] = sum[i];
			}
			
			for(int i = shortestLength; i < longestLength; i++){
				if(this.digits.length > val.digits.length){
					result[i] += this.digits[i];
				}else{
					if(this.digits.length < val.digits.length){
						result[i] += val.digits[i];
					}else{
						break;
					}
				} 
			}	
			for(int i = 0; i < result.length; i++){
				if(result[i] >= 10){
					result[i] -= 10;
					result[i + 1] += 1;
				}
			}
			
			String newString = "";
			if(!this.positive && !val.positive){
				newString += '-';
			}
			for(int i = result.length - 1; i >= 0; i--){
				newString = newString + result[i];	
			}

			return new BigInteger(newString);

		}else{
			if(!val.positive && this.positive){
				val.positive = true;
				return this.subtract(val);

			}else{
				this.positive = true;
				return val.subtract(this);
			}
		}
	}


	public BigInteger subtract (BigInteger val){

		if(this.positive && !val.positive){
			val.positive = true;
			return this.add(val);
		}
		if(!this.positive && val.positive){
			val.positive = false;
			return this.add(val);
		}
		if(this.compareMagnitude(val) ==  -1){
			return new BigInteger("-" + val.subtract(this));
		}	
		int shortestLength = minLength(this.digits, val.digits);
		int longestLength = maxLength(this.digits, val.digits);

		int[] result = new int[shortestLength + 1];
		for(int i = 0; i < shortestLength; i++){
			result[i] = this.digits[i] - val.digits[i];
		}
		for(int i = shortestLength; i < longestLength; i++){
			result[i] = this.digits[i];
		}

		for(int i = 0; i < result.length; i++){
			if(result[i] < 0){
				result[i] += 10;
				result[i + 1] -= 1;
			}
		}

		String newString = "";
		if(!this.positive && !val.positive){
			newString += '-';
		}
		for(int i = result.length - 1; i >= 0; i--){
			newString = newString + result[i];	
		}

		return new BigInteger(newString);



	}

	public BigInteger multiply(BigInteger val){

		int shortestLength = minLength(this.digits, val.digits);
		int longestLength = maxLength(this.digits, val.digits);
		int[] result = new int[shortestLength + longestLength];
		for(int i = 0; i < this.digits.length; i++){
			for(int j = 0; j < val.digits.length; j++){
				result[i + j] = this.digits[i] * val.digits[j];
			}
		}
		for(int i = 0; i < result.length; i++){
			if(result[i] >= 10){
				while(result[i] >= 10){
					result[i] -= 10;
					result[i + 1] += 1;
				}
			}
		}
		

		String newString = "";
		if((!this.positive && val.positive) || (this.positive && !val.positive)){
			newString += '-';
		}
		for(int i = result.length - 1; i >= 0; i--){
			newString = newString + result[i];	
		}

		return new BigInteger(newString);
	}




	public BigInteger divide (BigInteger val){
		/*if(this.compareMagnitude(val) == -1){
			return new BigInteger("0");
		}else{
			BigInteger result = new BigInteger("1");
			BigInteger intermediate = val;

			while(intermediate.multiply(BigInteger("10").compareMagnitude(this) == -1 || intermediate.multiply(BigInteger("10").compareMagnitude(this) == 0){
				intermediate = intermediate.multiply(BigInteger("10"));
				result = result.multiply(BigInteger("10"));
			}

			return result.add(this.divide(this.subtract(intermediate), val));

		}*/
		throw new UnsupportedOperationException();

		



	}


	public BigInteger remainder (BigInteger val){
		throw new UnsupportedOperationException();
	}


	
	public String toString(){
		String result = "";
		for(int i = this.digits.length - 1; i >= 0; i--){
			result += Integer.toString(this.digits[i]);
		}
		if(this.positive == false){
			result = "-" + result;
		}
		return result;
	}

	public int compareTo(BigInteger val){
		int result = 0;

		if(this.positive && !val.positive){
			result = 1;
		}
		if(!this.positive && val.positive){
			result = -1;
		}

		//If both have same sign
		if(this.positive == val.positive){
			if(this.length > val.length){
				result = 1;
			}
			if(this.length < val.length){
				result = -1;
			}

			if(this.length == val.length){
				for(int i = 0; i < this.length; i++){
					if(this.digits[i] > val.digits[i]){
						result = 1;
					}
					if(this.digits[i] < val.digits[i]){
						result = -1;
					}
				}
			}
			if(!this.positive && !val.positive){
				result = result * (-1);
			}
		}
		return result;	
	}

	public int compareMagnitude(BigInteger val){
		int result = 0;
		
		if(this.length > val.length){
			result = 1;
		}
		if(this.length < val.length){
			result = -1;
		}

		if(this.length == val.length){
			for(int i = 0; i < this.length; i++){
				if(this.digits[i] > val.digits[i]){
					result = 1;
				}
				if(this.digits[i] < val.digits[i]){
					result = -1;
				}
			}
		}
		
		return result;	
	}
	
	
	public boolean equals(Object x){
		return this.toString().equals(x.toString());

	}
	
	public static final BigInteger ZERO = new BigInteger("0");
	public static final BigInteger ONE = new BigInteger("1");
	public static final BigInteger TEN = new BigInteger("10");
	
	public static BigInteger valueOf (long val){
		throw new UnsupportedOperationException();
	}

	
	
}
