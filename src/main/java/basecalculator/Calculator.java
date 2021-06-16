package basecalculator;

import java.util.HashMap;
import java.util.Map;

public abstract class Calculator {

    private static String originalNumber; // num input for converter and calculator
    private static String secondNumber; // will eventually use this for the calculator

    private static int originalBase; // base converting from
    private static int newBase; // base converting to

    // maps to check the character for certain values
    // ex. 10 being A, and 35 being Z
    private static Map<String, Long> toDecimalMap = new HashMap<>() {{
        put("0", 0l);
        put("1", 1l);
        put("2", 2l);
        put("3", 3l);
        put("4", 4l);
        put("5", 5l);
        put("6", 6l);
        put("7", 7l);
        put("8", 8l);
        put("9", 9l);
        put("A", 10l);
        put("B", 11l);
        put("C", 12l);
        put("D", 13l);
        put("E", 14l);
        put("F", 15l);
        put("G", 16l);
        put("H", 17l);
        put("I", 18l);
        put("J", 19l);
        put("K", 20l);
        put("L", 21l);
        put("M", 22l);
        put("N", 23l);
        put("O", 24l);
        put("P", 25l);
        put("Q", 26l);
        put("R", 27l);
        put("S", 28l);
        put("T", 29l);
        put("U", 30l);
        put("V", 31l);
        put("W", 32l);
        put("X", 33l);
        put("Y", 34l);
        put("Z", 35l);
    }};
    private static Map<Long, String> fromDecimalMap = new HashMap<>() {{
        put(0l, "0");
        put(1l, "1");
        put(2l, "2");
        put(3l, "3");
        put(4l, "4");
        put(5l, "5");
        put(6l, "6");
        put(7l, "7");
        put(8l, "8");
        put(9l, "9");
        put(10l, "A");
        put(11l, "B");
        put(12l, "C");
        put(13l, "D");
        put(14l, "E");
        put(15l, "F");
        put(16l, "G");
        put(17l, "H");
        put(18l, "I");
        put(19l, "J");
        put(20l, "K");
        put(21l, "L");
        put(22l, "M");
        put(23l, "N");
        put(24l, "O");
        put(25l, "P");
        put(26l, "Q");
        put(27l, "R");
        put(28l, "S");
        put(29l, "T");
        put(30l, "U");
        put(31l, "V");
        put(32l, "W");
        put(33l, "X");
        put(34l, "Y");
        put(35l, "Z");
    }};




    // getters

    public static int getOriginalBase() {
        return originalBase;
    }

    public static Map<String, Long> getToDecimalMap() {
        return toDecimalMap;
    }

    public static Map<Long, String> getFromDecimalMap() {
        return fromDecimalMap;
    }



    // main functionality of UI
    public static void main(String[] args) {

        // instantiate basecalculator.UserInput
        // call getBase for original and new
        // call getNumber for original number
        // check if getBase returns -1 meaning invalid
        // check if getNumber returns null meaning invalid

        UserInput ui = new UserInput();
        SimpleConverter converter = new SimpleConverter();

        // get original base loop
        while (true) {
            System.out.print("Provide an starting base: ");
            originalBase = ui.getBase();
            if (originalBase == -1) {
                // -1 is returned if base entered was invalid
                System.out.println("Provide valid base.");
                continue;
            }
            break;
        }


        // get new base
        while (true) {
            System.out.print("Provide base to convert to: ");
            newBase = ui.getBase();
            if (newBase == -1) {
                System.out.println("Provide valid base.");
                continue;
            }
            break;
        }

        // get original number
        while (true) {
            System.out.print("Provide original number: ");
            originalNumber = ui.getNumber();
            if (originalNumber == null) {
                // null is returned if input is invalid
                // will later provide more contextual messages
                System.out.println("Use correct notation.");
                continue;
            }
            break;
        }
        // Single converter for entire process
        String newNumber = converter.convert(originalBase, newBase, originalNumber);

        // temporary output message
        System.out.println(originalNumber  + " in base " + originalBase + " is " + newNumber + " in base " + newBase);


    }
}
