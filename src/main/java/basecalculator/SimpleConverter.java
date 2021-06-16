package basecalculator;

import java.text.DecimalFormat;

public class SimpleConverter {


    /** One method taking in the original base, the base you're converting to
     * and the number you would like to convert.
     * Checks the input number for a floating point to determine if it should
     * be looking at fractionals. Converts Integral portion regardless, but
     * will do Fractional portion if written.
     *
     * @param oldBase
     * @param newBase
     * @param oldNumber
     * @return String containing notation of new number.
     */
    public String convert(int oldBase, int newBase, String oldNumber) {
        String newNumber = "";

        String integral;
        String fractional = "0";

        // first check for point
        if (oldNumber.contains(".")) {
            // if contains point, split into fractional and integral
            String[] oldArr = oldNumber.split("\\.");
            integral = oldArr[0];
            fractional = oldArr[1];
        } else integral = oldNumber; // otherwise store whole number into integral

        // convert integral
        // maintains accuracy while decimal value remains under 16 digits
        if (!integral.equals("0")) {
            // start sum at 0 and add values
            long sum = 0;
            // first convert to decimal
            for (int i = integral.length()-1, j = 0; i > -1; i--, j++) { // move right to left through string
                String currentChar = integral.substring(i, i+1); // pull current character of number out

                // check value of current character through toDecimalMap
                long currentValue = Calculator.getToDecimalMap().get(currentChar);

                // add it to sum, which will represent decimal at the end
                sum += currentValue * (Math.pow(oldBase, j));

            }

            // sum now contains the decimal value of the original inputs Integral portion


            // convert to newBase
            while (sum > 0) { // standard base conversion
                long remainder = sum % newBase; // take remainder of dividing decimal by new base

                // find character representing that value in the fromDecimalMap
                String newChar = Calculator.getFromDecimalMap().get(remainder);

                // concatenate that new character to the left of what was before
                // building right to left
                newNumber = newChar + newNumber;

                // reassign sum with the result of dividing by base, with fractional truncated off
                sum /= newBase;
            }

        }

        // convert fractional
        // currently doesn't work for more than 3-5 decimal places. Float and double inaccuracy affects outcome
        if (!fractional.equals("0")) { // will not run without floating point in input

            // same logic as integral with tracking decimal value using sum
            double sum = 0;

            // add floating point to the newNumber string
            newNumber += ".";

            // convert to decimal
            for (int i = 0; i < fractional.length(); i++) {

               // number * base ^ position
               // position being negative i - 1
                int position = (i * -1) - 1;

                // pulling current character
                String currentChar = fractional.substring(i, i+1);

                // finding value with map
                long currentValue = Calculator.getToDecimalMap().get(currentChar);

                // formatting digit length to maintain accuracy with doubles
                String formatString = "#.#";
                for (int j = 0; j < i; j++) {
                    // as the string increases in size, add more digits to format
                    formatString += "#";
                }

                // create formatter with previous string
                DecimalFormat df = new DecimalFormat(formatString);

                // hold temporary value to add to sum later
                double temp = (currentValue * (Math.pow(oldBase, position)));

                // parsing to double with format
                temp = Double.parseDouble(df.format(temp));

                // adding each value together for decimal
                sum += temp;

                // format sum before moving on to next
                sum = Double.parseDouble(df.format(sum));
            }

            // convert to new base
            // not accurate above 15 decimal places

            // holding integral portion after multiplying base new base
            long holdIntegral;

            // count to stop loop during infinitely repeating floating point numbers
            int count = 0;

            while (sum > 0 && count < 20) {

                // the int will chop off fractional portion
                // whether 0, 1, or 12 the integral portion is the number for current new number
                // going left to right this time
                holdIntegral = (long) (sum * newBase);

                // get value from map to concatenate to newNumber
                newNumber += Calculator.getFromDecimalMap().get(holdIntegral);

                // reassign sum like before, multiply by base but remove integral portion
                // repeat until 0
                sum = sum * newBase - holdIntegral;


                count++;
            }

        }







        return newNumber;
    }


}
