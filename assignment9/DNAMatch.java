/*
 * Name: Jelte Fennema
 * Studentnumber: 10183159
 * Study: Informatica
 *
 * Functionality:
 * A fixed version of the Program made by Dr. Quakerjack.
 */


/* 
 * DNAMatch v0.1
 * 
 * author  :  Dr. Quakerjack. 
 * date    :  17-11-2010 
 * version :  0.1 
 */
import java.util.ArrayList;
import java.util.Scanner;

public class DNAMatch {

   /*
    * added a ; and initialized the ArrayList.
    */
   public static ArrayList<String> database = new ArrayList<String>();

   /*
    * moved main to top.
    * removed initialization of the ArrayList.
    * removed duplicate code.
    */
   public static void main(String[] args) {
      System.out.println("Welcome to DNA Matcher v0.1\n");
      exeCnsl();
   }

   public static String rln() {
      Scanner input = new Scanner(System.in);
      if (input.hasNextLine()) {
         return input.nextLine();
      }
      return "Error";//added return statement outside if
   } //added bracket

   /*
    * made the function a void and added the different commands.
    */
   public static void helpuser() {
      System.out.println("COMMANDS...\n"
              + "Help: View a list of all the commands.\n"
              + "Add: Add a String to the database.\n"
              + "Compare: Compare two strings using Levenshtein.\n"
              + "Retrieve: Retreive the best matches for a string from the"
              + "database.\n"
              + "List: Show a list of everything in the database.\n"
              + "Remove: Remove a String from the database.\n"
              + "Quit: Quit the program.\n");
   }

   public static void printDatabase() {
      for (String s : database) {
         System.out.println(s);//added s
      }
      System.out.println();
   }

   /*
    * put doesn't exist as a method in the ArrayList class, so i replaced it 
    * with this.
    */
   public static void addToDatabase(String addString) {
      if (!database.contains(addString)) {
         database.add(addString);
      }
   }

   public static void removeFromDatabase(String i) {
      database.remove(database.indexOf(i));//delete replaced with remove
   }

   /*
    * removed dot in front of mss.
    */
   public static void compareStrings(String a, String aa) {
      System.out.println("Difference = " + levenshtein(a, aa, true));
   }    // Compare Strings

   /* Using Levensthein(?) algorithm. */
   public static int levenshtein(String a, String b, boolean c) {
      int[][] d = new int[a.length() + 1][b.length() + 1];
      // delete
      for (int i = 0; i <= a.length(); i++){//changed the declaration place of i
         d[i][0] = i; // changed j to i
      }
      // insert
      for (int j = 0; j <= b.length(); j++) {//same for j initialized it as 0
         d[0][j] = j;//changed i to j
      }
      for (int j = 1; j <= b.length(); j++) {
         for (int i = 1; i <= a.length(); i++) {
            if (a.charAt(i-1) == b.charAt(j-1)) {
               d[i][j] = d[i - 1][j - 1];
            } //added this bracket
            else {
               d[i][j] = min_3(
                       d[i - 1][j] + 1, 
                       d[i][j - 1] + 1, 
                       d[i - 1][j - 1] + 1);
            }
         }
      }
      if (c) {
         for (int l1 = 1; l1 <= a.length(); l1++) {//changed I1 and ll to l1
            for (int l2 = 1; l2 <= b.length(); l2++) {
               System.out.print(d[l1][l2] + "  ");//again here
            }
            System.out.println("\n");
         }
      }
      return d[a.length()][b.length()];
   }

   /* 
    * Return lowest int.
    * Made it readable and correct;
    */
   private static int min_3(int a, int b, int c) {
      if (a < b && a < c) {
         return a;
      }
      if (b < c) {
         return b;
      }
      return c;
   }

   public static void gfdb(String j) {// added public static void
      String[] dnaStrings = new String[database.size()];//used size method
      database.toArray(dnaStrings);
      int r = 0;
      int[] dnaInt = new int[database.size()];//again
      for (String s : database) {
         dnaInt[r] = levenshtein(j, s, false);
         r++;//took the r++ out of the previous statement.
      }
      boolean exchanged = true;
      int checkSize = dnaStrings.length;
      while (exchanged) {
         exchanged = false;
         for (int i = 1; i < checkSize; i++) {
            if (dnaInt[i] < dnaInt[i - 1]) {
               int t1 = dnaInt[i];
               dnaInt[i] = dnaInt[i - 1];
               dnaInt[i - 1] = t1;
               String t2 = dnaStrings[i];
               dnaStrings[i] = dnaStrings[i - 1];
               dnaStrings[i - 1] = t2;
               exchanged = true;
            }
         }
         checkSize--;
      }


      System.out.println("Best matches: ");

      for (r = 0; r < Math.min(3, dnaStrings.length); r++) {
         System.out.println(dnaInt[r] + "\t" + dnaStrings[r]);
      }
   }

   /* 
    * User interface.
    */
   public static void exeCnsl() {
      boolean quit = false;
      do {
         System.out.print("console> ");
         String A;
         A = rln();
         String[] P = A.split("\\s");
         String C = P[0].toUpperCase();
         if (C.equals("HELP")) {//changed all Strings to upper case and use
            helpuser();         //equals method instead of ==
         } else if (C.equals("QUIT")) {
            quit = true;
         } else if (C.equals("LIST")) {
            printDatabase();
         } else if (C.equals("ADD")) {
            addToDatabase(P[1]);
         } else if (C.equals("REMOVE")) {//added space
            removeFromDatabase(P[1]);
         } else if (C.equals("COMPARE")) {
            compareStrings(P[1], P[2]);
         } else if (C.equals("RETRIEVE")) {
            gfdb(P[1]);
         } else {
            System.out.println("Skipping...");
         }
      } while (!quit);//changed statement
   }
}