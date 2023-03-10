package j1.l.pkg0002;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Manager {

    private final String UPCHOICE_VALID = "u|U|up|Up|UP|D|d|delete|Delete|DELETE";
    Student s = new Student();
    Validation ch = new Validation();
    ArrayList<Student> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int count = 0;
    int b;
    private String idTest;
    private final String CHOICE_VALID = "y|Y|yes|Yes|YES|n|N|no|No|NO";

    /**
     * Add value
     */
    public void demo() {
        if (list.isEmpty()) {
            System.out.println("List Empty!");
        } else {
            System.out.println("Genarate " + list.size() + " students successfully");
        }

    }

    /**
     * Check input choice with regex
     *
     * @param s
     * @param a
     * @param b
     * @return
     */
    public String checkChoice(String s, String a, String b) {
        while (true) {;
            try {
                String result = sc.nextLine().trim();
                if (!result.matches(s)) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please choice again (" + a + "/" + b + ")");
                System.out.print("Enter again: ");
            }
        }
    }

    /**
     * Check update data
     *
     * @param s1
     * @param a1
     * @param b1
     * @return
     */
    public boolean checkUpdate(String s1, String a1, String b1) {
        boolean check = false;
        String s = checkChoice(s1, a1, b1);
        if (s.equalsIgnoreCase("u") || s.equalsIgnoreCase("up") || s.equalsIgnoreCase("update")) {
            check = true;
        }
        return check;
    }

    /**
     * Menu function
     */
    public void menu() {
        generateStudent();
        while (true) {
            System.out.println("WELCOME TO STUDENT MANAGEMENT");
            System.out.println(" 0.Demo");
            System.out.println(" 1.Create");
            System.out.println(" 2.Find and Sort");
            System.out.println(" 3.Update/Delete");
            System.out.println(" 4.Report");
            System.out.println(" 5.Exit");
            System.out.print("Please enter choice: ");
            int choice = ch.checkInputIntLimit(0, 5);
            switch (choice) {
                case 0:
                    demo();
                    break;
                case 1:
                    System.out.println("--- Add student ---");
                    createStudent();
                    break;
                case 2:
                    if (list.isEmpty()) {
                        System.out.println("List Empty");
                    }
                    System.out.println("--- Search student ---");
                    find();
                    break;
                case 3:
                    if (list.isEmpty()) {
                        System.out.println("List Empty");
                    } else {
                        disPlayStudent();
                        boolean checkID = checkIdExist();
                        if (checkID) {
                            System.out.print("Do you want to update (U) or delete (D) student? ");
                            boolean check = checkUpdate(UPCHOICE_VALID, "U", "D");
                            if (check) {
                                System.out.println("--- Updating ---");
                                update();
                                System.out.println("--- Update complete ---");
                            } else {
                                delete();
                                System.out.println("--- Delete complete ---");
                            }
                        }
                        System.out.println();
                    }
                    break;
                case 4:
                    if (list.isEmpty()) {
                        System.out.println("List Empty");
                    }
                    System.out.println("--- Report student ---");
                    report();
                    break;
                case 5:
                    System.out.println("SEE YOU AGAIN");
                    return;
            }

        }
    }

    /**
     * Add data to array if array is empty message when you choose to add to the
     * array, it will notify you if you want to add it
     */
    public void createStudent() {
        //disPlayStudent();

        s = new Student();

        if (list.size() < 10) {
            s = new Student();
            s.checkId();
            boolean checkId = false;
            for (Student m : list) {
                if (m.getID().equalsIgnoreCase(s.getID())) {
                    s.setStudentName(m.getStudentName());
                    checkId = true;
                }
            }
            if (checkId) {
                System.out.println("ID exist");
                s.checkSemester();
                s.checkCourseName();
                list.add(s);
                System.out.println("--- Add successfully ---");
                disPlayStudent();
                return;
            } else {
                s.checName();
                s.checkSemester();
                s.checkCourseName();
                list.add(s);
                disPlayStudent();
            }
        } else {
            while (true) {
                boolean yn = ch.checkInputYN();
                if (!yn) {
                    break;
                } else {
                    s = new Student();
                    s.checkId();
                    boolean checkId = false;
                    for (Student m : list) {
                        if (m.getID().equalsIgnoreCase(s.getID())) {
                            s.setStudentName(m.getStudentName());
                            checkId = true;
                        }
                    }
                    if (checkId) {
                        System.out.println("ID exist");
                        s.checkSemester();
                        s.checkCourseName();
                        list.add(s);
                        System.out.println("--- Add successfully ---");
                        disPlayStudent();
                        return;
                    } else {
                        s.checName();
                        s.checkSemester();
                        s.checkCourseName();
                        list.add(s);
                        disPlayStudent();
                    }
                }
            }
        }

    }

    /**
     * Show data in array
     */
    public void disPlayStudent() {
        System.out.println("+-----+------------------+--------------+--------------+");
        System.out.println("| ID  | Student name     | Semester     | Course       |");
        System.out.println("+-----+------------------+--------------+--------------+");
        for (Student s : list) {
            System.out.printf("| %-4s| %-17s| %-13s| %-13s|\n", s.getID(), s.StudentName, s.semester, s.courseName);
        }
        System.out.println("+-----+------------------+--------------+--------------+");
    }

    /**
     * Sort student list by name Find and reorder data when you search by name
     */
    public void find() {
        Collections.sort(list);
        if (list.isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.print("Enter student name: ");
            String nameSearch = sc.nextLine().trim().toLowerCase();
            int num = 0;
            for (Student o : list) {
                if (o.getStudentName().toLowerCase().contains(nameSearch)) {

                    if (num == 0) {
                        System.out.println("+--------------------------+----------+----------+");
                        System.out.printf("|       Student name       | Semester |  Course  |\n");
                        System.out.println("+--------------------------+----------+----------+");
                    }
                    System.out.printf("| %-25s| %-9s| %-9s|\n", o.getStudentName(), o.getSemester(), o.getCourseName());
                    num++;
                }
            }
            if (num > 0) {
                System.out.println("+--------------------------+----------+----------+");
            } else {
                System.out.println("Student with name '" + nameSearch + "' is not existed!");
            }
        }
    }

    /**
     * Show information when user enter search name
     *
     * @param idx
     */
    public void disPlayFind(int idx) {
        Student q = list.get(idx);
        System.out.printf("| %-25s| %-9s| %-9s|\n", q.StudentName, q.semester, q.courseName);
    }

    /**
     * Check Id
     *
     * @return
     */
    public boolean checkIdExist() {
        s = new Student();
        while (true) {
            System.out.print("Enter student ID: ");
            idTest = sc.nextLine().trim();
            boolean checkID = false;
            for (Student o : list) {
                if (o.getID().equals(idTest)) {
                    checkID = true;
                    break;
                }
            }
            if (checkID) {
                break;
            } else {
                System.out.println("ID do not exist on the list");
                System.out.print("Do you want to continue (Y/N)?");
                boolean check = ch.checkInputYN();
                if (!check) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Show data
     *
     * @param listStudentFindByID
     */
    public void displayListStudentByID(ArrayList<Student> listStudentFindByID) {
        int count = 0;
        System.out.println("+-----+--------------------------+----------+----------------+");
        System.out.printf("| No. |       Student name       | Semester |     Course     |\n");
        System.out.println("+-----+--------------------------+----------+----------------+");
        for (Student o : listStudentFindByID) {
            System.out.printf("|%4s | %-25s| %-9s| %-15s|\n", count, o.getStudentName(), o.getSemester(), o.getCourseName());
            count++;
        }
        System.out.println("+-----+--------------------------+----------+----------------+");
    }

    /**
     * Display list of student for update/delete purpose
     *
     * @param listStudentFindByID
     * @return
     */
    public Student getStudentByListFound(ArrayList<Student> listStudentFindByID) {
        System.out.println("List student found: ");
        displayListStudentByID(listStudentFindByID);
        //int a = listStudentFindByID.size();
        int choice = inputInt("Enter choice " + "(" + 0 + ", " + (listStudentFindByID.size() - 1) + "): ",
                0, listStudentFindByID.size() - 1);
        return listStudentFindByID.get(choice);
    }

    /**
     * Check data when user enter
     *
     * @param mess
     * @param min
     * @param max
     * @return
     */
    public int inputInt(String mess, int min, int max) {
        System.out.print(mess);
        //force user input exectly integer number
        while (true) {
            String input = sc.nextLine();
            try {
                int number = Integer.parseInt(input);
                //check range of number
                if (number < min || number > max) {
                    System.out.print("Please input between " + min + ", " + max + ": ");
                    continue;
                }
                return number;
            } catch (Exception e) {
                System.out.print("Please input an integer number: ");
            }
        }
    }

    /**
     * Get list of student find by ID
     *
     * @param id
     * @return
     */
    public ArrayList<Student> getListStudentByID(String id) {
        ArrayList<Student> getListStudentById = new ArrayList<>();
        for (Student student : list) {
            if (id.equalsIgnoreCase(student.getID())) {
                getListStudentById.add(student);
            }
        }
        return getListStudentById;
    }

    /**
     * Update the data when the user wants to change the data If array has no
     * empty list message
     */
    public void update() {
        s = new Student();
        ArrayList<Student> listStudentFindByID = getListStudentByID(idTest);
        Student student = getStudentByListFound(listStudentFindByID);
        s.setID(idTest);
        s.checName();
        s.checkSemester();
        s.checkCourseName();
        for (Student o : list) {
            if (o.getID().equalsIgnoreCase(idTest) && o.getStudentName().equalsIgnoreCase(s.getStudentName())
                    && o.getCourseName().equalsIgnoreCase(s.getCourseName())
                    && o.getSemester().equalsIgnoreCase(s.getSemester())) {
                System.out.println("Duplicate");
                break;
            } else {
                if (!s.getStudentName().equalsIgnoreCase(student.getStudentName())) {
                    for (Student change_name : list) {
                        if (change_name.getID().equals(idTest)) {
                            change_name.setStudentName(s.getStudentName());
                        }
                    }
                }
                student.setStudentName(s.getStudentName());
                student.setSemester(s.getSemester());
                student.setCourseName(s.getCourseName());
                break;
            }
        }
        displayListStudentByID(listStudentFindByID);
    }

    /**
     * Delete the data when the user wants to delete the data If array has no
     * empty list message
     */
    public void delete() {
        ArrayList<Student> listStudentFindByID = getListStudentByID(idTest);
        Student student = getStudentByListFound(listStudentFindByID);
        list.remove(student);
        listStudentFindByID.remove(student);
        displayListStudentByID(listStudentFindByID);
    }

    /**
     * show data when update or delete
     *
     * @param idx
     */
    public void disPlayId(int idx) {
        Student q = list.get(idx);
        System.out.printf("| %-4d| %-4s|%-19s| %-13s| %-13s|\n", count, q.ID, q.StudentName, q.semester, q.courseName);

    }

    /**
     * Report data
     */
    public void report() {
        if (list.isEmpty()) {
            System.out.println("List Empty");
        } else {
            System.out.println("+-----+-----+--------------------------+----------+-----------------+");
            System.out.println("| No. | ID  | Student name             | Course   | Total of course |");
            System.out.println("+-----+-----+--------------------------+----------+-----------------+");
            HashMap<String, Integer> reports = new HashMap<>();
            for (Student s : list) {
                String key = s.getID() + "#" + s.getStudentName() + "#" + s.getCourseName();
                if (reports.containsKey(key)) {
                    int total = reports.get(key);
                    reports.put(key, total + 1);
                } else {
                    reports.put(key, 1);
                }
            }
            int n = 1;
            for (String key : reports.keySet()) {
                System.out.format("| %4s| %3s | %-25s| %-9s| %16s|\n", n, key.split("#")[0], key.split("#")[1], key.split("#")[2], reports.get(key));
                ++n;
            }
            System.out.println("+-----+-----+--------------------------+----------+-----------------+");
        }

    }

    /**
     * Show list in array
     */
    public void generateStudent() {
        list.add(new Student("s1", "long", "fall", "Java"));
        list.add(new Student("s1", "long", "summer", ".Net"));
        list.add(new Student("s1", "long", "fall", "C/C++"));
        list.add(new Student("s2", "van", "spring", "Java"));
        list.add(new Student("s3", "toan", "fall", "C/C++"));
        list.add(new Student("s4", "nam", "fall", "Java"));
        list.add(new Student("s5", "ha", "summer", "Java"));
        list.add(new Student("s6", "man", "fall", "Java"));
        list.add(new Student("s2", "van", "fall", "Java"));
        list.add(new Student("s5", "ha", "fall", ".Net"));

    }

    /**
     * Check data when enter index
     *
     * @param func
     * @return
     */
    public int checkIndex(boolean func) {
        int n = 0;
        boolean a = false;
        do {
            try {
                n = Integer.parseInt(sc.nextLine());
                if (func && (n < 0 || n > b)) {
                    System.out.print("Please enter index 0 to " + b + ": ");
                } else {
                    a = true;
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter index 0 to " + b + ": ");
            }
        } while (!a);
        return n;
    }
}
