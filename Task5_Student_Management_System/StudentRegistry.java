import java.util.ArrayList;

public class StudentRegistry {

    // Stores all students
    private ArrayList<StudentRecord> students = new ArrayList<>();

    // Used to auto-generate student IDs
    private int idCounter = 1001;

    // Adds new student
    public void addStudent(String name, int age, String course) {
        StudentRecord student = new StudentRecord(idCounter++, name, age, course);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    // Finds student by ID
    private StudentRecord findStudentById(int id) {
        for (StudentRecord s : students) {
            if (s.getStudentId() == id) {
                return s;
            }
        }
        return null;
    }

    // Updates student info
    public void updateStudent(int id, String name, int age, String course) {
        StudentRecord student = findStudentById(id);

        if (student != null) {
            student.updateDetails(name, age, course);
            System.out.println("Student details updated.");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    // Deletes student
    public void removeStudent(int id) {
        StudentRecord student = findStudentById(id);

        if (student != null) {
            students.remove(student);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    // Displays all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        for (StudentRecord s : students) {
            s.showInfo();
        }
    }
}
