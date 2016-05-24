public class GeneralizedChangemaker {

	public static void main(String[] args) {
		if (args.length != 2) {
			printUsage();
			return;
		}

		try {
			int amount = Integer.parseInt(args[0]);
			if (amount < 0) {
				System.out.println("Change cannot be made for negative amounts.");
				System.out.println();
				printUsage();
				return;
			}

			String[] denominationStrings = args[1].split(",");
			int[] denominations = new int[denominationStrings.length];

			for (int i = 0; i < denominations.length; i++) {
				denominations[i] = Integer.parseInt(denominationStrings[i]);
				if (denominations[i] <= 0) {
					System.out.println("Denominations must all be greater than zero.");
					System.out.println();
					printUsage();
					return;
				}

				for (int j = 0; j < i; j++) {
					if (denominations[j] == denominations[i]) {
						System.out.println("Duplicate denominations are not allowed.");
						System.out.println();
						printUsage();
						return;
					}
				}
			}

			Tuple change = makeChangeWithDynamicProgramming(denominations, amount);
			if (change.isImpossible()) {
				System.out.println("It is impossible to make " + amount + " cents with those denominations.");
			} else {
				int coinTotal = change.total();
				System.out.println(amount + " cents can be made with " + coinTotal + " coin" +
				getSimplePluralSuffix(coinTotal) + " as follows:");

				for (int i = 0; i < denominations.length; i++) {
					int coinCount = change.getElement(i);
					System.out.println("- "  + coinCount + " " + denominations[i] + "-cent coin" +
		  			getSimplePluralSuffix(coinCount));
				}
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Denominations and amount must all be integers.");
			System.out.println();
			printUsage();
		}
	}

	public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int amount) {

		int numOfDenoms = denominations.length;
		Tuple[][] tuples = new Tuple[numOfDenoms][amount + 1];


		for(int i = 0; i < numOfDenoms; i++){
			tuples[i][0] = new Tuple(numOfDenoms);
		}

		for(int row = 0; row < tuples.length; row++){
			for(int column = 1; column < tuples[row].length; column++){
				int remainder = column - denominations[row];
				//tuples[row][column] = new Tuple(numOfDenoms);


				if(remainder >= 0){
					
					tuples[row][column] = new Tuple(numOfDenoms);
					tuples[row][column].setElement(row, 1);
					if(tuples[row][remainder].isImpossible()){
						tuples[row][column] = Tuple.IMPOSSIBLE;
					}
					else{
						tuples[row][column] = tuples[row][column].add(tuples[row][remainder]);
					}
					
				}else{
					tuples[row][column] = Tuple.IMPOSSIBLE;
				}

				if(row != 0){

					if(tuples[row-1][column].isImpossible() && tuples[row][column].isImpossible()){
						tuples[row][column] = Tuple.IMPOSSIBLE;
					}
					if( (tuples[row][column].isImpossible() && !tuples[row-1][column].isImpossible()) ){
						tuples[row][column] = tuples[row - 1][column];	
					}
					else if((!tuples[row][column].isImpossible() && tuples[row-1][column].isImpossible()) || (tuples[row-1][column].total() > tuples[row][column].total())){
						tuples[row][column] = tuples[row][column];
					}
					else if (tuples[row - 1][column].equals(tuples[row][column])){
						tuples[row][column] = tuples[row][column];
					}
					else{
						tuples[row][column] = tuples[row - 1][column];
					}
				}
				
			}
		}

		//dump(tuples);
		return tuples[numOfDenoms - 1][amount];

	}

	private static void printUsage() {
		System.out.println("Usage: java GeneralizedChangemaker <amount> <denominations>");
		System.out.println("  - <amount> is the amount for which to make change");
		System.out.println("  - <denominations> is a comma-separated list of denominations (no spaces)");
	}

	private static String getSimplePluralSuffix(int count) {
		return count == 1 ? "" : "s";
	}

	private static void dump(Tuple[][] tuples){
		for(int row = 0; row < tuples.length; row++){
			for(int column = 0; column < tuples[row].length; column++){
				System.out.print(tuples[row][column] + " ");
			}
			System.out.println();
		} 

	}

}
