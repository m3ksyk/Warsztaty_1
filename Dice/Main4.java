package Dice;

import java.util.Random;
import java.util.Scanner;

/**
 * this program simulates dice throws
 *  xDy+z
 *  where:
 *  x - number of throws
 *  Dy- type of dice (eg. D4, D6, D10, D20, D100)
 *  +z (optional) - perform optional operation
 *  (SUPPORTED:  1. addition ( + )
 *               2. subtraction ( - )
 *               3. multiplication ( * )
 *               4. division ( / )
 *
 *  WARNING! input validity check not implemented ATM please use valid input type ONLY until proper functions are implemented
 *  WARNING! crashes if input is xDy (without optional operation)

 */
public class Main4 {
    public static void main(String[] args) {
        //gets input, table of values and calculation type
        String input = getInput();
        String calcType = getCalcType(input);
        int[] vals = getValsTab(input, calcType);
        System.out.println(vals[0]);
        System.out.println(vals[1]);
        System.out.println(vals[2]);

        //does the dice throwing using given data
        double result = calculateDiceRoll(vals[0], vals[2], vals[1], calcType);
    }
    /**
     * returns a random number which is within given bound
     * @param randBound
     */
    static int getRandNum(int randBound) {
        Random generator = new Random();
        int randNum = generator.nextInt(randBound);
        while( randNum == 0){
            randNum = generator.nextInt(randBound);
        }
        System.out.println("Random is: " + randNum);
        return randNum;
    }
    /**
     * the chosen dice is "thrown" number of times equal to numThrow
     *  the results of throws are added, then the optional calculation is performed
     *  based on user input, the final result is then returned and printed
     * @param numThrow
     * @param opt
     * @param randBound
     * @param calcType
     * @return
     */
    static double calculateDiceRoll(int numThrow, int opt, int randBound, String calcType){
        double diceRollResult = 0;
        // add dice throws
        try {
            for (int i = 0; i < numThrow; i++) {
                int rand = getRandNum(randBound);
                diceRollResult = diceRollResult + rand;
                System.out.println("temp result " + diceRollResult);
            }
            //select and perform the optional calculation
            switch (calcType) {
                case "\\+": diceRollResult += opt;
                    break;
                case "\\-": diceRollResult -= opt;
                    break;
                case "\\*": diceRollResult *= opt;
                    break;
                case "\\/": diceRollResult /= opt;
                    break;
                default: diceRollResult = diceRollResult;
                    break;
            }
            System.out.println("Result is : " + diceRollResult);
            return diceRollResult;
        }catch (ArithmeticException e){
            System.out.println("Error. Division by zero");
        }
        return 0;
    }
    /**
     * this method gets user input
     */
    static String getInput(){
        //get the input from console
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Write what kind of dice throw you would like to simulate");
            String input = scanner.nextLine();
            return input;
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
        //catch format and null pointer exceptions
    }
    /**
     * method returns type of optional calculation
     * @param input
     * @return
     */
    static String getCalcType(String input){
         String calcType;
                if (input.contains("+")){
                    calcType = "\\+";
                } else if(input.contains("-")){
                    calcType = "\\-";
                }else if(input.contains("*")){
                    calcType= "\\*";
                }else if(input.contains("/")){
                    calcType = "\\/";
                } else {
                    System.out.println("no optional calculation");
                    calcType = null;
                }
                return calcType;
    }
    /**
     * this method creates a table of values used in the calculations from user input
     * @param input
     * @param calcType
     * @return
     */
    static int[] getValsTab(String input, String calcType){
        int[] vals = new int[3];

        //get number of throws
        //gets everything that is before 'D'
        String[] beforeD = input.split("\\D");
        System.out.println("number of throws: " + beforeD[0]);
        int numThrow = Integer.parseInt(beforeD[0].toString());
        vals[0] = numThrow;

        //get type of dice
        //gets everything that is between 'D' and '+' in input string and parses it to an integer
        String subInput = input.substring(input.lastIndexOf("D") + 1);
        int randBound = 0;
        if(calcType.equals(null)){
            randBound = Integer.parseInt(subInput);
        } else{
            String[] beforePlus = subInput.split(calcType);
            System.out.println("Type of Dice: " + beforePlus[0]);
            randBound = Integer.parseInt(beforePlus[0].toString());
        }
        vals[1] = randBound;

        //get optional value
        //gets everything that is behind calculation symbol in input string and parses it to an integer
        if(!calcType.equals(null)) {
            int opt = Integer.parseInt(input.substring(input.lastIndexOf(calcType.charAt(1)) + 1));
            System.out.println("optional: " + opt);
            vals[2] = opt;
        } else{
            vals[2] = 0;
        }
        return vals;
    }
}