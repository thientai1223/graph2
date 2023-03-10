package j1.l.pkg0002;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Validation {

    ArrayList<Student> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Student s = new Student();

    /**
     * Check data when user enter function
     *
     * @param min
     * @param max
     * @return
     */
    public int checkInputIntLimit(int min, int max) {
        //loop until user input correct
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in range [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    /**
     * Check data when user enter yes or no
     *
     * @return
     */
    public boolean checkInputYN() {

        System.out.print("Do you want to order now y/Y or n/N: ");
        while (true) {
            String result = sc.nextLine();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

}
