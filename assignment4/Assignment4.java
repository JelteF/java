/*
 * Name: Jelte Fennema
 * Studentnumber: 10183159
 * Study: Informatica
 *
 * Functionality: The program needs to do two things.
 * 1. Differentiate different kinds of strings.
 *      a. Sentences, strings with a space in them.
 *      b. Series of numbers, strings with more numbers than letters.
 *      c. Words, the rest of the strings.
 *
 * 2. Check if a given string is a palindrome. 
 */

import java.util.*;

public class Assignment4 {

    /*
     * Main method: Call the palindrome method.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        palindrome(sc);
    }


    /*
     * Palindrome method: Asks for input and makes the input uppercase.
     * Calls methods to check what kind of string it is.
     * Calls the method to filter the unwanted characters out.
     * Checks if that string as a palindrome by comparing the first and last
     * character, then the second and second last character and so on.
     */
    public static void palindrome(Scanner sc) {
        do {
            String filtered = "";
            System.out.println("Insert string here:");
            if (sc.hasNextLine()) {
                String input = sc.nextLine();
                input = toUpperCase(input);
                if (isSentence(input)) {
                    filtered = filterSentence(input);
                }
                else if(isNumberSerie(input))
                {
                    filtered = filterNumberSerie(input);
                }
                else if(isWord(input)){
                    filtered = filterWord(input);
                }            
            }
            boolean palindrome = true;
            System.out.println("This is the string that will be tested: " + filtered);
            for (int i = filtered.length()-1, j = 0; i >= 0; i--, j++){
                char first, last;
                first = filtered.charAt(j);
                last = filtered.charAt(i);
                if(first != last){
                    palindrome = false;
                    break;
                }
            }
            if (palindrome)
                System.out.println("The given string is a palindrome.");
            else {
                System.out.println("The given string is not a palindrome");
            }
        } while (retry(sc));
    }


    /*
     * IsSentence method: Checks if the given string is a sentence by checking
     * if it contains spaces.
     */
    static boolean isSentence(String serie){
        for (int i=0; i < serie.length(); ++i){
            if(serie.charAt(i) == 32) {
                System.out.println("It's a sentence.");
                return true;
            }
            
        }
        return false;
    }

    /*
     * IsNumberSerie method: Checks if the given string is a serie of numbers
     * by checking if it contains more numbers than letters.
     */
    static boolean isNumberSerie(String serie){
        int numbers = 0, letters = 0;
        int charNumber;
        for (int i=0; i < serie.length(); ++i){
            charNumber = serie.charAt(i);
            if(charNumber >= 48 && charNumber <= 57) {
                ++numbers;
            }
            else if(charNumber >= 65 && charNumber <= 90) {
                ++letters;
            }
        }
        if (numbers>letters) {
            System.out.println("It's a serie of numbers.");
            return true;
        }
        return false;
    }

    /*
     * IsWord function: Checks if the string is a word by checking that it's not
     * a sentence or a serie of numbers. 
     */
    static boolean isWord(String serie){
        if (isSentence(serie) || isNumberSerie(serie))
            return false;
        System.out.println("It's a word.");
        return true;
    }


    /*
     * FilterSentence method: Filters every character out of the string that
     * is not a letter or a space.
     */
    static String filterSentence(String serie){
        String output = "";
        int charNumber;
        for (int i=0; i < serie.length(); ++i){
            charNumber = serie.charAt(i);
            if(charNumber >= 65 && charNumber <= 90 || charNumber == 32) {
                output += serie.charAt(i);
            }
        }
        return output;
    }

     /*
     * FilterNumberSerie method: Filters every character out of the string that
     * is not a number.
     */
    static String filterNumberSerie(String serie){
        String output = "";
        int charNumber;
        for (int i=0; i < serie.length(); ++i){
            charNumber = serie.charAt(i);
            if(charNumber >= 48 && charNumber <= 57) {
                output += serie.charAt(i);
            }
        }
        return output;
    }

    /*
     * FilterSentence method: Filters every character out of the sentence that
     * is not a letter.
     */
    static String filterWord(String serie){
        String output = "";
        int charNumber;
        for (int i=0; i < serie.length(); ++i){
            charNumber = serie.charAt(i);
            if(charNumber >= 65 && charNumber <= 90) {
                output += serie.charAt(i);
            }
        }
        return output;
    }


    /*
     * toUpperCase method: Replaces all lower case letters in a string with
     * upper case letters, because we weren't allowed to juse the toUpperCase
     * function from the String class. It does this by reducing the char value
     * by 32 for every lower case letter.
     */
    static String toUpperCase(String serie){
        String output = "";
        int charNumber;
        for (int i=0; i < serie.length(); ++i){
            charNumber = serie.charAt(i);
                if(charNumber >= 97 && charNumber <= 122) {
                output += (char) (serie.charAt(i) - 32);
            }
            else {
                output += serie.charAt(i);
            }
        }
        return output;
    }
    
    
    /*
     * Retry method: Return true if the function has to execute again. It's the
     * same one I used as in Assignment3, but with replacements for the 
     * toUpperCase and the equals method from the String class.
     */
    public static boolean retry (Scanner sc) {
        System.out.println("\nDo you want execute this function once more? "
                + "[Y/N]");
        boolean yN = false;
        boolean stupid = true;
        do {
            if(sc.hasNextLine()) {
                String answer = sc.nextLine();
                if(toUpperCase(answer).charAt(0)=='Y' && answer.length()==1) {
                    yN = true;
                    stupid = false;
                } 
                else if(toUpperCase(answer).charAt(0)=='N' &&
                        answer.length()==1) {
                    yN = false;
                    stupid = false;
                } 
                else {
                    stupid = true;
                    System.out.println("\nReally, is it so hard to just type an"
                            + " Y or an N? \nPlease try that again.");
                }
            }
        } while(stupid);
        System.out.println("");
        return yN;
    }
}