package GuessANumber;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * this is a simple guessing game
 * program chooses a random number between 1 and 100
 * user guesses the number and inputs it in the console
 * user is then prompted, whether his number is the correct one,
 * is smaller or bigger and provides number of tries it took to guess right
 * If user input is invalid (other than number or out of range)
 * user is prompted to re-enter valid input
 */

public class Main1 {
    public static void main(String[] args) {
        int num = getRandNum();
        System.out.println("let's play a game: guess a number between 1 and 100.");
        System.out.println("Enter a number:");
        int userGuess = getNum();
        numberGuess(num, userGuess);
    }
    /**
     * compares random number with user input
     * and returns answers
     */
    static void numberGuess(int num, int userGuess) {
        int counter = 1;
        while(userGuess != num) {
            if (userGuess > num) {
                System.out.println("Your guess is too big!");
                counter++;
            } else if (userGuess < num) {
                System.out.println("Your guess is too small!");
                counter++;
            }
            userGuess = getNum();
        }
        System.out.println("Congratulations! you guessed it! The number was: " + num);
        System.out.println("It took you " + counter + " tries to get the correct number.");
    }
    /**
     * returns a random number
     */
    static int getRandNum() {
        Random generator = new Random();
        int num = generator.nextInt(100);
        while( num == 0){
            num = generator.nextInt( 100);
        }
        return num;
    }
    /**
     * returns a number given by user
     */
    static int getNum() {
        int userGuess = 0;
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            try {
               // userGuess = scanner.nextInt();
                userGuess = Integer.parseInt(scanner.nextLine());
                if (userGuess <= 100 && userGuess > 0){
                return userGuess;
                } else {
                    System.out.println("Value out of range! Enter a number between 1 and 100 : ");
                }
            } catch (Exception e) {
                System.out.println("Not a number! Enter a number:");
               // return -1;
            }
        }
        return 0;
    }
}

