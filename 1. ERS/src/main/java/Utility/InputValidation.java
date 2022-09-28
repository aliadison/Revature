package Utility;

import MainMenu.ERS;

public class InputValidation {

	public static int enterInt(int min, int max, String msg) {
		int number;
		do {
			while (true)
				try {
					System.out.print(msg);
					number = Integer.parseInt(ERS.scan.nextLine());
					break;
				} catch (NumberFormatException nfe) {
					System.out.println("That's not an option!");
				}
			if (number < min || number > max )
				System.out.println("That's not an option!");

		} while (number < min || number > max );

		return number;
	}
	
	public static int enterPositiveInt(String msg) {
		int number;
		do {
			while (true)
				try {
					System.out.print(msg);
					number = Integer.parseInt(ERS.scan.nextLine());
					break;
				} catch (NumberFormatException nfe) {
					System.out.println("Not a valid input!");
				}
			if (number < 1)
				System.out.println("Not a valid input!");

		} while (number < 1);

		return number;
	}
	
	public static char enterChar(String charChoices, String msg) {
		String regexChoices = "["+ charChoices + "]";
		char input= Character.MIN_VALUE;
		
		System.out.print(msg);
		while (!ERS.scan.hasNext(regexChoices)) {
			System.out.println("Not a valid input!");
			System.out.println();
			System.out.print(msg);
			input = ERS.scan.nextLine().toLowerCase().charAt(0);
		}
		input = ERS.scan.nextLine().toLowerCase().charAt(0);

		return input;
	}
	
	public static String enterString(String msg) {
		String myString = "";
		
		boolean validString = false;
		while(!validString) {
			System.out.print(msg);
			myString = ERS.scan.nextLine();
			if(!myString.equals(""))
				validString = true;
		}
		
		return myString;
	}
}
