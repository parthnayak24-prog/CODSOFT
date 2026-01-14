import java.util.Scanner;

public class StudentApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StudentRegistry registry = new StudentRegistry();
        boolean appRunning = true;

        System.out.println("==================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM    ");
        System.out.println("==================================");

        while (appRunning) {

            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    addStudentFlow(registry);
                    break;

                case 2:
                    updateStudentFlow(registry);
                    break;

                case 3:
                    deleteStudentFlow(registry);
                    break;

                case 4:
                    registry.displayAllStudents();
                    break;

                case 5:
                    appRunning = false;
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Menu display
    private static void showMenu() {
        System.out.println("\n1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. View All Students");
        System.out.println("5. Exit");
        System.out.print("Choose option: ");
    }

    // Flow for adding student
    private static void addStudentFlow(StudentRegistry registry) {

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter course: ");
        String course = scanner.nextLine();

        if (name.isEmpty() || course.isEmpty() || age <= 0) {
            System.out.println("Invalid input. Student not added.");
            return;
        }

        registry.addStudent(name, age, course);
    }

    // Flow for updating student
    private static void updateStudentFlow(StudentRegistry registry) {

        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new course: ");
        String course = scanner.nextLine();

        registry.updateStudent(id, name, age, course);
    }

    // Flow for deleting student
    private static void deleteStudentFlow(StudentRegistry registry) {

        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Are you sure? (y/n): ");
        char confirm = scanner.nextLine().toLowerCase().charAt(0);

        if (confirm == 'y') {
            registry.removeStudent(id);
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}
