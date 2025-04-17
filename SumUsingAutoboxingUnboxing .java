import java.util.ArrayList;
import java.util.Scanner;

public class SumUsingAutoboxingUnboxing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        // Step 1: Read input
        System.out.print("Enter a list of integers separated by spaces: ");
        String input = scanner.nextLine();

        // Step 2: Split and parse each number using Integer.parseInt()
        String[] parts = input.trim().split("\\s+");
        for (String part : parts) {
            try {
                int num = Integer.parseInt(part); // parse string to int
                numbers.add(num); // Autoboxing: int → Integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input detected and skipped: " + part);
            }
        }

        // Step 3: Calculate the sum using unboxing
        int sum = 0;
        for (Integer number : numbers) {
            sum += number; // Unboxing: Integer → int
        }

        // Step 4: Display result
        System.out.println("The sum of the integers is: " + sum);
        scanner.close();
    }
}
