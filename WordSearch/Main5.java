package WordSearch;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        String siteName = "https://www.onet.pl/";
        String inputFileName = "popular_words.txt";
        String outputFileName = "filtered_popular_words.txt";
        String[] wordsToIgnore = {"oraz", "ponieważ", "gdzie", "więc", "dlatego", "dlaczego",
                        "jego", "jutro", "wczoraj", "musi", "jeszcze", "mniej", "trzeba", "kiedy",
                        "wtedy", "jest", "będzie", "który", "jaka", "taka", "znów"};

        performAllSteps(siteName, wordsToIgnore, inputFileName, outputFileName);
    }
    /**
     * this method calls all other methods and performs all the stepds of the program
     * @param siteName
     * @param wordsToIgnore
     */
    static void performAllSteps(String siteName, String[] wordsToIgnore, String inputFileName, String outputFileName){
        System.out.println("Work in progress...");
        ArrayList<String> cutList = cutLessThanThree(readWebsite(siteName));
        writeToFile(inputFileName, cutList);
        ArrayList<String> list2 =  readFromFile(inputFileName);
        writeToFile(outputFileName, filter(list2, wordsToIgnore));
        System.out.println("Finished.");
    }

    /**
     * method reads a website, and gets words from titles, saves them to an ArrayList
     * @param siteName
     * @return
     */
    static ArrayList<String> readWebsite(String siteName){

        ArrayList<String> list = new ArrayList<>();
        siteName ="http://www.onet.pl/";
        //connect to page and get the HTML
        Connection connect = Jsoup.connect(siteName);

        try{
            Document document = connect.get();
            Elements links = document.select("span.title");
            //Get text from the page, without HTML markers, read all lines and separate words
            for (Element elem : links) {
                BufferedReader reader = new BufferedReader(new InputStreamReader
                        (new ByteArrayInputStream(elem.text().getBytes())));

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split(" ");

                    //fills an ArrayList with words
                    for (String word : words) {
                        if (!list.contains(word)) {
                            list.add(word.replaceAll("[^a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+", ""));
                        }
                    }
                }
               // int listSize = list.size();
               // System.out.println("List size " + listSize);
                reader.close();
            }
            return list;
        }catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * method returns an arrayList containing words from input list longer than 3 characters
     * @param list
     * @return cutList
     */
    static ArrayList<String> cutLessThanThree(ArrayList<String> list){
        ArrayList<String> cutList = new ArrayList<>();
        for(String word : list){
            if (word.length() > 3){
                cutList.add(word);
            }
        }
        return cutList;
    }

    /**
     * this method writes words from arrayList to a file
     * @param fileName
     * @param list
     */
    static void writeToFile(String fileName,  ArrayList<String> list) {
        try {
            Path path = Paths.get(fileName);

            if(!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.write(path, list, StandardOpenOption.WRITE);

        } catch (IOException ex) {
            System.out.println("Could not save the file.");
        }
    }
    /**
     * method retrieves an arrayList from file
     * @param fileName
     * @return cutList
     */
    static ArrayList<String> readFromFile(String fileName){
        ArrayList<String> outList = new ArrayList<>();
        try {
            Path path = Paths.get(fileName);

            //adds all text from input file
            Scanner scan = new Scanner(path);
            while(scan.hasNextLine()){
                outList.add(scan.nextLine());
            }
            scan.close();
            return outList;
        }catch (NoSuchElementException e) {
            e.printStackTrace();
        }catch (IOException ex) {
            System.out.println("could not save the file.");
        }catch (FileSystemNotFoundException e) {
            System.out.println("Missing file.");
        }
        return outList;
    }
    /**
     * method removes ignored words from input list and returns an array
     * @param list
     * @param wordsToIgnore
     * @return
     */
    static  ArrayList<String> filter( ArrayList<String> list, String[] wordsToIgnore){
        ArrayList<String> filtered = new ArrayList<>();
        ArrayList<String> ignored = new ArrayList<>(Arrays.asList(wordsToIgnore));
        //System.out.println(list);
        //removes all ignored words, adds them to filtered list
        list.removeAll(ignored);
        //System.out.println(list);
        filtered.addAll(list);
        return filtered;
    }
}