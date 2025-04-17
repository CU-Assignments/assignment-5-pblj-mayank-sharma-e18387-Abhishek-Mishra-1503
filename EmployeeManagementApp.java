import java.io.*;
import java.util.*;

// Step 1: Employee class with Serializable
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public void display() {
        System.out.println("ID         : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Designation: " + designation);
        System.out.println("Salary     : ₹" + salary);
        System.out.println("---------------------------");
    }
}

public class EmployeeManagementApp {
    private static final String FILE_NAME = "employees.dat";

    // Read all employees from file
    @SuppressWarnings("unchecked")
    public static List<Employee> readEmployeesFromFile() {
        List<Employee> employees = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (List<Employee>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File might not exist yet, ignore
        } catch (Exception e) {
            System.out.println("❌ Error reading employees: " + e.getMessage());
        }
        return employees;
    }

    // Write all employees to file
    public static void writeEmployeesToFile(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("❌ Error saving employees: " + e.getMessage());
        }
    }

    // Add an employee
    public static void addEmployee(Scanner scanner) {
        try {
            System.out.print("Enter Employee ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Designation: ");
            String designation = scanner.nextLine();

            System.out.print("Enter Salary: ₹");
            double salary = Double.parseDouble(scanner.nextLine());

            Employee employee = new Employee(id, name, designation, salary);
            List<Employee> employees = readEmployeesFromFile();
            employees.add(employee);
            writeEmployeesToFile(employees);

            System.out.println("✅ Employee added successfully.\n");

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Please enter correct data types.\n");
        }
    }

    // Display all employees
    public static void displayEmployees() {
        List<Employee> employees = readEmployeesFromFile();
        if (employees.isEmpty()) {
            System.out.println("⚠️ No employees found.\n");
        } else {
            System.out.println("\n📋 Employee List:");
            for (Employee emp : employees) {
                emp.display();
            }
        }
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("====== Employee Management System ======");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        addEmployee(scanner);
                        break;
                    case 2:
                        displayEmployees();
                        break;
                    case 3:
                        System.out.println("🚪 Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("❌ Invalid choice. Please try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid numeric choice.\n");
                choice = 0;
            }

        } while (choice != 3);

        scanner.close();
    }
}
