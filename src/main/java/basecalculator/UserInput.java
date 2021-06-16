package basecalculator;

import java.util.Map;
import java.util.Scanner;

public class UserInput {

    // used for fromBase and toBase
    public int getBase() {
        Scanner scanner = new Scanner(System.in);
        int output; // output to be returned

        // get input
        String userInput = scanner.nextLine();
        try {
            // validating int input
            output = Integer.parseInt(userInput);
        } catch (Exception e) {
            output = -1;
            // return -1 if invalid
        }

        // validating if input is in range of converter
        if (output < 2 || output > 36) output = -1; // return -1 if invalid

        return output;
    }

    // used for original number, checks against original base regardless
    public String getNumber() {
        Scanner scanner = new Scanner(System.in);
        String output; // returned number

        // get original base to check if number is in correct notation
        int fromBase = Calculator.getOriginalBase();

        // get input
        output = scanner.nextLine().toUpperCase();

        // validate input
        Map<String, Long> mapCheck = Calculator.getToDecimalMap();

        // boolean for later checking if multiple floating points
        boolean hasPoint = false;


        try {
            // iterate through number as string
            for (int i = 0; i < output.length(); i++) {
                // converting current letter to string for faster use with map
                String currentLetter = String.valueOf(output.charAt(i)).toUpperCase();

                // ignore floating point
                if (currentLetter.equals(".") && !hasPoint) {
                    // if encounters first floating point
                    hasPoint = true;
                    continue;
                } else if (currentLetter.equals(".") && hasPoint) {
                    // if encounters second or more floating points
                    output = null;
                    break;
                }

                // checking if each character value less than or equal to base
                // ex. Base 10 uses 0-9, Base 5 uses 0-4
                // character used must be 0 through one less than base
                if ( fromBase <= mapCheck.get(currentLetter) ) {

                    // return null if string isn't within base given
                    output = null;
                }
            }
        } catch (NullPointerException e) {
            // if the character used is not in the map, like symbols
            output = null;
        }

        return output;
    }

}
