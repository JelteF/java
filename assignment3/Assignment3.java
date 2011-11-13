import java.util.Scanner; 


/*
 * Name: Jelte Fennema
 * Studentnumber: 10183159
 * Study: Informatica
 * 
 * Functionality: The program needs to do two things.
 * 1. Calculate the given amount of Lucas numbers. The first to Lucas numbers
 * are 2 and 1 and the rest are the sum of the previous two.
 * Like this: 2 1 3 4 7 11 ...
 * 
 * 2. Calculate the power when the input is the base and the exponent.
 */

public class Assignment3 {

    /*
     * Main method: Ask for the input, check if the input is usefull and if so,
     * forward it to the other methods.
     */
    public static void main(String[] args) {
        /*
         * Input for Lucas numbers.
         */
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("How many Lucas numbers do you want?");
            if(sc.hasNextInt())
                lucas(sc.nextInt());
            else
                System.out.println("Please, use whole numbers.");
        } while (retry(sc));
        
        /*
         * Input for power function.
         */
        int base, exp;
        do {
            System.out.println("What is the base of the power?");
            if(sc.hasNextInt()){
                base = sc.nextInt();
                System.out.println("\nWhat is the exponent of the power?");
                if (sc.hasNextInt()){
                    exp = sc.nextInt();
                    if(base == 0 && exp <= 0) 
                        System.out.println("\n0^" + exp + " is undefined.");
                    else
                        System.out.println("\n" + base + "^" + exp + " = " 
                            + power(base, exp));
                }
                else
                    System.out.println("Please, use whole numbers.");
            }
            else
                System.out.println("Please, use whole numbers.");
        } while (retry(sc));
    }
    
    /*
     * Calculate the power like this: base^exp and return the answer.
     */
    static double power(int base, int exp) {
        if (base == 0)
            return 0;
        else if (exp == 0)
            return 1;
        else if (exp > 0) {
            double answer = base;
            for (int i = 1; i < exp; ++i){
                answer *= base;
            }
            return answer;
        }
        else {
            double answer = base;
            exp *=-1;
            for (int i = 1; i < exp; ++i){
                answer *= base;
            }
            answer = 1.0/answer;
            return answer;
        }
    }
    
    /*
     * Lucas method: Print the given amount of Lucas numbers.
     */
    static void lucas(int n) {
        int n1 = 2, n2 = 1, n3;
        if (n > 1){
            System.out.println("\n2\n1");
            for(int i = 2; i < n; ++i){
                n3 = n2;
                n2 += n1;
                n1 = n3;
                System.out.println(n2);
            }
        }
        else if (n == 1)
            System.out.println("\n2");

        else
            System.out.println("\nPlease, use positive numbers next time");
    }
    
    /*
     * Retry method: Return true if the function has to execute again.
     */
    public static boolean retry (Scanner sc) {
        System.out.println("\nDo you want execute this function once more? [Y/N]");
        boolean yN = false;
        boolean stupid = true;
        do {
            if(sc.hasNext()) {
                String answer = sc.next();
                if(answer.toUpperCase().equals("Y")) {
                    yN = true;
                    stupid = false;
                } 
                else if(answer.toUpperCase().equals("N")) {
                    yN = false;
                    stupid = false;
                } 
                else {
                    stupid = true;
                    System.out.println("\nReally, is it so hard to just type an Y"
                            + " or an N? \nPlease try that again.");
                }
            }
        } while(stupid);
        System.out.println("");
        return yN;
    }
}
