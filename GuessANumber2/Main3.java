package GuessANumber2;

import java.util.Random;
import java.util.Scanner;

/**
 * this is a simple man vs machine guessing game.
 * Think of a number between 1 and 1000, the machine
 * will try to guess it.
 * type:
 *  - "hit" if machine guessed right (don't cheat)
 *  - "less" if the number is smaller
 *  - "more" if the number is bigger
 *  the machine will prompt you if it thinks you are cheating
 *  the machine should win within 10 tries
 */
public class Main3 {
    public static void main(String[] args) {
        System.out.println("think of a number between 1 and 1000, I will guess it in under 10 tries");
        Random generator = new Random();
        Scanner scanner = new Scanner(System.in);
        int min =0;
        int max =1000;
        String userAnswer;
        int guess = generator.nextInt(1000);

        int counter = 0;
        do {
            //guess= ((max - min)/2) + min;
            System.out.println("Is it " + guess);
            userAnswer = scanner.nextLine();

            if(userAnswer.equals("less")) {  //too low
                max = guess;
                counter++;
                guess= ((max - min)/2) + min;
            }
            else if(userAnswer.equals("more")) { // too high
                min = guess;
                counter++;
                guess= ((max - min)/2) + min;
            } else {
                System.out.println("Stop cheating");
                guess= ((max - min)/2) + min;
            }
        } while(!userAnswer.equals("hit"));

        System.out.println("I won. The number is " + guess + ". It took me " + counter + " tries to get it right");

    }
}