/*
 * Name: Jelte Fennema
 * Studentnumber: 10183159
 * Study: Informatica
 *
 * Functionality: Add all the natural numbers lower or the same as the given number
 */


public class Opgave1 {;
/*
 * Main method: Takes the first given number, give it to the sum method.
 *
 */
    public static void main(String[] args) {
        int n    = Integer.parseInt(args[0]);
    }
/*
 * Sum method: Adds all the integers lower than an the same as given and print that value.
 */
    public static int sum(int k) {
        if (k <= 0){
            System.out.println("The number is lower than zero so there is no answer");
            return;
        }
        if (k > 65535){
            System.out.println("The given number is to high, the program encountered an overflow");
            return;
        }
        int ksum = 1;
        while (k > 0) {
            ksum = ksum + k;
            k = k - 1;
        }
		System.out.println(k);
    }
}
