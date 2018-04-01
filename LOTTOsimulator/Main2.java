package LOTTOsimulator;

import java.util.*;

/**
 * this is a simple LOTTO lottery simulator
 * the random numbers generator chooses
 * 6 non-repeating numbers (range 1 to 49)
 * user inputs 6 numbers of choice (if input is invalid
 * eg. a letter or out of range, user is prompted)
 * The program then proceeds to check how many of numbers are correct
 * and informs the user of the result
 */
public class Main2 {
    public static void main(String[] args) {
        //getting all the numbers
        Integer[] winNums = getWinNums();
        Integer[] chosen = getChosenNums();
        //commencing number check
        winCheck(winNums, chosen);
    }

    /**
     * checks how many numbers given by user are correct
     * and returns the result
     * @param winNums
     * @param chosen
     */
    static void winCheck(Integer[] winNums, Integer[] chosen) {
        //compares two arrays
        int match = 0;
        for (int i = 0; i<chosen.length; i++) {
            for (int j = 0; j <winNums.length; j++){
                if (chosen[i] == winNums[j]) {
                    match++;
                    System.out.println("HIT: " + chosen[j]);
                }
            }
        }
        switch (match){
            case 3 : System.out.println("WIN! You have 3 numbers right");
                        break;
            case 4 : System.out.println("WIN! You have 4 numbers right");
                        break;
            case 5 : System.out.println("WIN! You have 5 numbers right");
                        break;
            case 6 : System.out.println("WIN! You have 6 numbers right");
                        break;
            default: System.out.println("Sorry, you did not win. Better luck next time");
                        break;
        }
    }

    /**
     * gets user's numbers of choice
     * @return
     */
    static Integer[] getChosenNums() {
        //input of numbers by user
        //creates an Array list, checks if input does not repeat
        //and adds numbers to the list
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> chosenList = new ArrayList<>();
        int in = 0;
        System.out.println("Please input 6 numbers (do not repeat numbers):");

        //fills until list size is 6
        while(chosenList.size() != 6) {
            //check if input is a number
            while (!scan.hasNextInt())
            {
                scan.next();
                System.out.print("Please enter a NUMBER: ");
            }
            //reads in a value from console
            in = (scan.nextInt());
            //checks if number is within range
            if (in < 49 && in != 0 ) {
                //checks if list contains input, if not, adds to list
                if (!chosenList.contains(in)) {
                    chosenList.add(in);
                } else{
                    System.out.println("number already added, try another number");
                }
            }else {
                System.out.println("input must be a number within range of 1 to 49");
                System.out.println("try another number:");
            }
        }
        //puts lists contents into an arrays and sorts
        Integer[] chosen = chosenList.toArray(new Integer[0]);
        Arrays.sort(chosen);
        return chosen;
    }

    /**
     * generates 6 random non-repeating numbers from 1 to 49
     * @return
     */
    static Integer[] getWinNums() {
        //fills array with numbers 1-49 without repeating
        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        //shuffles the numbers within array
        Collections.shuffle(Arrays.asList(arr));

        //adds first 6 numbers to arrayList and sorts them
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i< 6; i++) {
            list.add(arr[i]);
        }
        Integer[] winNums = list.toArray(new Integer[0]);
        Arrays.sort(winNums);
        return winNums;
    }
}
