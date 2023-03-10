package j1.l.pkg0002;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Create value and check data
 *
 * @author Admin
 */
public class Student implements Comparable<Student> {

    String ID;
    String StudentName;
    String semester;
    String courseName;
    String listCourseName[] = {"Java", ".Net", "C/C++"};
    ArrayList<Student> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    /**
     * Create new student
     *
     * @param ID
     * @param StudentName
     * @param semester
     * @param courseName
     */
    public Student(String ID, String StudentName, String semester, String courseName) {
        this.ID = ID;
        this.StudentName = StudentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    Student() {
    }

    /**
     * Get Id
     *
     * @return
     */
    public String getID() {
        return ID;
    }

    /**
     * Set id
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Get student name
     *
     * @return
     */
    public String getStudentName() {
        return StudentName;
    }

    /**
     * Set student name
     *
     * @param StudentName
     */
    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    /**
     * Get semester
     *
     * @return
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Set semester
     *
     * @param semester
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Get course name
     *
     * @return
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set course name
     *
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Check data when enter user semester
     *
     * @return
     */
    public String checkId() {
        while (true) {
            try {
                System.out.print("Please enter ID: ");
                ID = sc.nextLine();
                if (ID.trim().isEmpty()) {
                    throw new MyException("empty");
                }
                setID(ID);
                break;
            } catch (MyException e) {
            }
        }
        return ID;
    }

    /**
     * Check data when enter user name
     *
     * @return
     */
    public String checName() {

        while (true) {
            try {
                System.out.print("Please enter student name: ");
                StudentName = sc.nextLine().toLowerCase();
                if (StudentName.trim().isEmpty()) {
                    throw new MyException("empty");
                }
                if (!StudentName.matches("[a-z A-Z]+")) {
                    throw new MyException("matches-name");
                }
                setStudentName(StudentName);
                break;
            } catch (MyException e) {
            }
        }
        return StudentName;

    }

    /**
     * Check data when enter user semester
     *
     * @return
     */
    public String checkSemester() {

        while (true) {
            try {
                System.out.print("Please enter semester: ");

                semester = sc.nextLine();
                if (semester.trim().isEmpty()) {
                    throw new MyException("empty");
                }
                if (!semester.matches("[a-z A-Z 0-9]+")) {
                    throw new MyException("matches");
                }
                setSemester(semester);
                break;
            } catch (MyException e) {
            }
        }
        return semester;
    }

    /**
     * Check data when enter user course name
     *
     * @return
     */
    @SuppressWarnings("empty-statement")
    public String checkCourseName() {
        System.out.print("Please enter course: ");
        while (true) {
            courseName = sc.nextLine().trim().toUpperCase();
            if (courseName.equalsIgnoreCase("C") || courseName.equalsIgnoreCase("c++") || courseName.equalsIgnoreCase("c/c++")) {
                setCourseName("C/C++");
                break;
            } else if (courseName.equalsIgnoreCase(".net")) {
                setCourseName(".Net");
                break;
            } else if (courseName.equalsIgnoreCase("java")) {
                setCourseName("Java");
                break;
            } else if (courseName.isEmpty()) {
                System.out.print("Course name can not be empty!\n"
                        + "Please enter again (Java/.Net/C/C++): ");
            } else {
                System.err.println("There are only three courses: Java, .Net and C/C++");
                System.out.print("Please enter again: ");
            }
        }
        return courseName;
    }

    /**
     * compare student name
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Student o) {
        return o.StudentName.compareTo(this.StudentName);
    }

}
