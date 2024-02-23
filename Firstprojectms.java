/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.firstprojectms;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author muhammedsimsek
 */
public class Firstprojectms {

    

    // -------------Block that checks whether the newly added word is a digit.---------------
    public static boolean isdigit(String newWord) {
        boolean foundDigit = false;

        for (int i = 0; i < newWord.length(); i++) {
            char c = newWord.charAt(i);
            if (Character.isDigit(c)) {
                foundDigit = true;
                break; // If a number is found, terminate the loop.
            }
        }

        if (foundDigit) {
            System.out.println("Sorry, we're looking for text input. Please provide a word.");
        }

        return !foundDigit; // If no numbers are found, return true.
    }

//--------------------------------------------------------------------------------------------------
    //--------------------------It checks the new adding words' length ---------------------------   
    public static boolean letterLength (String newWord) {
        boolean morethan3 = true;

        if (newWord.length() <= 3) {
            System.out.println("You need to enter more than three character");
            morethan3 = false;

        }
        return morethan3;

    }
//----------------------------------------------------------------------------------------------    

    public static void game(ArrayList<String> names,ArrayList<Integer> point) {
        int index = (int) (Math.random() * names.size());
        String gameWord = names.get(index);

        int right = gameWord.length() / 2;
        int howMany = 0;
        System.out.println("You have " + right + " rights");

        char[] result = new char[gameWord.length()];
        for (int i = 0; i < gameWord.length(); i++) {
            result [i] = '_';
            System.out.printf("%-2c", '_');
            // All elements in the char array are currently underscore (_) characters. 
        }
        
        
        while (right > 0) {
            System.out.println();
            System.out.print("Select one char: ");
            Scanner scnr = new Scanner(System.in);
            String guessString = scnr.nextLine().toLowerCase();

            if (guessString.length() == 1 && Character.isLetter(guessString.charAt(0))) /*
                Here, we expect the user to enter a letter, but the user can also enter a digit 
                or multiple letters; we handle this situation.
                */
            {
                char guessletter = guessString.charAt(0);
                boolean correctGuess = false;

                for (int i = 0; i < gameWord.length(); i++) {
                    if (gameWord.charAt(i) == guessletter) {
                        result[i] = guessletter;
                         
                        correctGuess = true;
                         howMany++;
                        
              // "Here, the reason for a correct guess is that one letter has been guessed correctly, triggering the 'if' statement."
                    }
                    
                    System.out.printf("%-2c", result[i]);
                    // This statement is outside the 'if' block because I want to print it in every scenario.
                }

                if (!correctGuess) // This part will work if the 'correctGuess' is not true.
                {
                    right--;
                    System.out.println("There is not " + guessletter);

                } else {
                    System.out.println("There is " + howMany + " ' " + guessletter + " ' ");
                }
                howMany=0;

                boolean completed = true;
                for (int i = 0; i < result.length; i++) {
                    if (result[i] == '_') {
                        /*Here, as can be understood, I am checking individual indices
                        one by one to see if there is an '_' character; if there is, it means it is not completed. 
                        */
                        completed = false;
                      
                        break;
                    }
                }

                if (completed) {
                    System.out.println("You win");
                    point.add(10);
                    break;

                }
            } else {
                System.out.println("Invalid input. Please enter a single letter.");
            }
        }

        if (right == 0) { 
            point.add(0);
            System.out.println("Sorry! You ran out of rights. The word was: " + gameWord);
        }
    }

//---------------------------Selects a new word from the list------------------------------------------    
    //-------------------------------------------------------------------------------------------
    public static boolean addAword(ArrayList<String> names) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("1- Cannot contain numbers.\n"
                + "2- Must be at least three letters long.\n"
                + "3- The same word cannot be added twice.");
System.out.print("Enter the word you want to add: ");
        String newWord = scnr.nextLine();
        boolean wordAdded = true;

        if (isdigit(newWord) && letterLength(newWord) && !names.contains(newWord)) {
            names.add(newWord);
            System.out.println("The word has been successfully added.: " + newWord);

        } else {
            wordAdded = false;
            if (names.contains(newWord)) {
                System.out.println("The word has already been added before.");
            }
        }

        return wordAdded;
    }

    @SuppressWarnings("empty-statement")
    public static void menu(ArrayList<String> names ,ArrayList<Integer> point) {
        Scanner scnr = new Scanner(System.in);
        int options = 0;

        do {
           System.out.println("1- Add Word\n"
                    + "2- New Game\n"
                    + "3- View Scores\n"
                    + "4- Exit");
                System.out.print("Option: ");
            if (scnr.hasNextInt()) {
                options = scnr.nextInt();

                switch (options) {
                    case 1:
                        if (addAword(names)) {
                            // Actions to be taken when a word is added.
                        } else {
                            // Actions to be taken when a word is not added.
                        }
                        break;
                    case 2:
                       game(names,point);
                        break;
                    case 3:
                        System.out.println(point);
                        break;
                    case 4:
                        System.out.println("Exiting the program...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } else {
                // If the user hasn't entered an integer.
                System.out.println("Error: Please enter a number.");
                scnr.next(); // clean the Buffer.
                options = 0; // Reset the option.
            }

        } while (options != 4);
        scnr.close();

    }

    public static void main(String[] args) {

       ArrayList<Integer> point = new ArrayList<>();

        ArrayList<String> names = new ArrayList<>();
        names.add("ali");
        names.add("mehmet");
        names.add("cem");
        names.add("ömer");
        names.add("bilal");
        names.add("umut");
        names.add("muhammed");
        names.add("talha");

        menu(names,point);

    }

}
