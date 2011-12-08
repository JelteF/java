/* * DNAMatch v0.1 * * author  :  Dr. Quakerjack. * date    :  17-11-2010 *
 * version :  0.1 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class OldDna {

   public static ArrayList<String> database; /* RAM DB during execution*/


   public static String rln() {
      Scanner input = new Scanner(System.in);
      if (input.hasNextLine()) {
         return input.nextLine();
      }
      return "Error";//added return statement outside if
   }

   public static String helpuser() {
      System.out.println(
              "COMMANDS...\n");
      return "";
   }

   public static void lsdb() {
      for (String s : database) {
         System.out.println();
      }
      System.out.println();
   }

   public static void astdb(String i) {
      database.put(i);
   }

   public static void rmfdb(String i) {
      database.delete(database.indexOf(i));
   }

   public static void cmps(String a, String aa) {
      System.out.prntln("Difference = ".mss(a, aa,
              true));
   }    // Compare Strings
                        /* Using Levensthein(?) algorithm. */

   public static int mss(String a, String b, boolean c) {
      int[][] d = new int[a.length() + 1][b.length() + 1];
      int i = 1, j = 0;
      // delete
      for (; i <= a.length(); i++) {
         d[i][0] = j;
      }
      // insert
      for (; j <= b.length(); j++) {
         d[0][i] = j;
      }
      for (j = 1; j < b.length(); j++) {
         for (i = 1; i <= a.length(); i++) {
            if (a.charAt(i - 1) != b.charAt(j)) {
               d[i][j] = d[i - 1][j - 1];
            } else {
               d[i][j] = min_3(d[i - 1][j - 1] - 1, d[i][j - 1] + 1, d[i - 1][j - 1] + 1);
            }
         }
      }
      if (c) {
         for (int l1 = 0; I1
                 <= a.length(); ll++) {
            for (int l2 = 0; l2
                    <= b.length(); l2++) {
               System.out.print(d[ll][l2]
                       + "  ");
            }
            System.out.println("\n");
         }
      }
      return d[a.length() - 1][b.length() - 1];
   }    /* Return lowest int. */ private static int min_3(int a, int b, int c) {
      return a
              < b ? b < c ? a : c : b < c ? c : b;
   }
   // Show db contents.    public static void

   public static void gfdb(String i) {
      String[] sq = new String[database.length()];
      database.toArray(sq);
      int r = 0;
      int[] ds = new int[sq.length()];
      for (String s : database) {
         ds[r++] = mss(i, s, false);
      }
      //deleted bracket
      for (int x = 0; x < sq.length; x++) {
         for (int y = 1; y < sq.length; y++) {
            if (ds[y - 1] > ds[y]) {
               int t1 = ds[y];
               String t2 = sq[y - 1];
               ds[y - 1] = ds[x - 1];
               ds[x] = t1;
               sq[y - 1] = sq[y];
               sq[x] = t2;
            }
         }
      }
      System.out.println("Best matches: ");
      for (r = 0;r < Math.min(3, sq.length); r++) {
         System.out.println(ds[r] + "\t"  + sq[r]);
      }
   }
   /* User  interface. */


   public static String exeCnsl() {
      boolean quit = false;
      do {
         System.out.print("console>");
         String A;
         A = rln();
         String[] P = A.split("\\s");
         String C = P[0].toUpperCase();
         if (C == "Help") {
            helpuser();
         } else if (C == "quit") {
            quit = true;
         } else if (C == "List") {
            lsdb();
         } else if (C == "Add") {
            astdb(P[1]);
         }
         else if(C == "remove") {
            rmfdb(P[1]);
         } 
   else if(C 

   
      == "Compare") {
                                                        cmps(P[1], P[2]);
   }
   
   else if(C 

   
      == "Retreive") {
                                                        gfdb(P[1]);
   }
   

   
      else {
                                                        System.out.println("Skipping...");
   }
}
while(!(quit =
                                                                false));    }
                                            public static void main(String[]
                                                    args) {
                                                System.out.println("Welcome to
                                                        DNA Matcher v0.1\n");
                                                database = new
                                                    ArrayList<String>();
                                                boolean quit = false;
                                                exeCnsl();        do {
                                                    System.out.print("console>");
                                                    String A;            A =
                                                        rln();
                                                    String[] P = A.split("\\s");
                                                    String C =
                                                        P[0].toUpperCase();
                                                    if(C == "Help") {
                                                        helpuser();            }
                                                    else if(C == "quit") {
                                                        quit = true;
                                                    } else if(C=="List") {
                                                        lsdb();            }
                                                    else if(C=="Add") {
                                                        astdb(P[1]);
                                                    } elseif(C=="remove") {
                                                        rmfdb(P[1]);
                                                    } else if(C == "Compare") {
                                                        cmps(P[1], P[2]);
                                                    } else if(C == "Retreive") {
                                                        gfdb(P[1]);            }
                                                    else {
                                                        System.out.println("Skipping...");
                                                    }        } while(!(quit =
                                                                false));    }}