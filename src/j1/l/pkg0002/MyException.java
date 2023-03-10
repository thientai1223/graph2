package j1.l.pkg0002;

/**
 *
 * @author Admin
 */
public class MyException extends Exception {

    String red = "\u001B[31m", reset = "\u001B[0m", blue = "\u001B[34m";
    String type;

    /**
     * Error checking when type is null
     */
    public MyException() {
        super();
        this.type = null;
    }

    /**
     * Error message
     *
     * @param type
     */
    public MyException(String type) {
        if (type.equals("empty")) {
            System.out.println(red + "You have not entered input data." + reset);
        }
        if (type.equals("zero")) {
            System.out.println(red + "Does not allow input data less than or equal to 0." + reset);
        }
        if (type.equals("matches")) {
            System.out.println(red + "Allow input data contain a-z, A-Z, and 0-9." + reset);
        }
        if (type.equals("matches-name")) {
            System.out.println(red + "Allow input data contain a-z, A-Z." + reset);
        }
        if (type.equals("matches-number")) {
            System.out.println(red + "Allow input data contain 0 - 9." + reset);
        }
        if (type.equals("course")) {
            System.out.println(red + "Course does not exist. There are only three courses: "
                    + "Java, .Net, C/C++" + reset);
        }
        if (type.equals("yn")) {
            System.out.println(red + "Allow input data contain Y or N." + reset);
        }
        if (type.equals("ud")) {
            System.out.println(red + "Allow input data contain U or D." + reset);
        }
    }

    /**
     * return value
     *
     * @return
     */
    @Override
    public String getMessage() {
        return "";

    }

}
