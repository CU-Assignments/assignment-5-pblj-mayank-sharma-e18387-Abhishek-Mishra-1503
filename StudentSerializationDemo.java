import java.io.*;

// Step 1: Define Student class implementing Serializable
class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double gpa;

    // Constructor
    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // Method to display student details
    public void displayDetails() {
        System.out.println("Student Details:");
        System.out.println("ID   : " + id);
        System.out.println("Name : " + name);
        System.out.println("GPA  : " + gpa);
    }
}

public class StudentSerializationDemo {
    // Serialize method
    public static void serializeStudent(Student student, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(student);
            System.out.println("✅ Student object has been serialized to " + filename);
        } catch (IOException e) {
            System.out.println("❌ Serialization Error: " + e.getMessage());
        }
    }

    // Deserialize method
    public static Student deserializeStudent(String filename) {
        Student student = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            student = (Student) ois.readObject();
            System.out.println("✅ Student object has been deserialized from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("❌ File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("❌ IO Error during deserialization: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Class not found: " + e.getMessage());
        }
        return student;
    }

    public static void main(String[] args) {
        String filename = "student.dat";

        // Step 2: Create a Student object
        Student student = new Student(101, "Alice", 3.85);

        // Step 3: Serialize the object
        serializeStudent(student, filename);

        // Step 4: Deserialize the object and display
        Student deserializedStudent = deserializeStudent(filename);
        if (deserializedStudent != null) {
            deserializedStudent.displayDetails();
        }
    }
}
