public class StudentRecord {

    private int studentId;
    private String name;
    private int age;
    private String course;

    public StudentRecord(int studentId, String name, int age, String course) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getStudentId() {
        return studentId;
    }

    public void updateDetails(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public void showInfo() {
        System.out.println("--------------------------------");
        System.out.println("ID     : " + studentId);
        System.out.println("Name   : " + name);
        System.out.println("Age    : " + age);
        System.out.println("Course : " + course);
    }
}
